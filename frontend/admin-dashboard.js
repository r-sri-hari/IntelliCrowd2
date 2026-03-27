let emergencyState = "OFF";

function loadUsers() {
    console.log("Users section skipped for now");
    const table = document.getElementById("usersTableBody");
    if (table) {
        table.innerHTML = `
            <tr>
                <td colspan="6">Client user API not connected yet</td>
            </tr>
        `;
    }
}

function updateHeatmap(level) {
    const lowZone = document.getElementById("lowZone");
    const mediumZone = document.getElementById("mediumZone");
    const highZone = document.getElementById("highZone");

    if (!lowZone || !mediumZone || !highZone) return;

    lowZone.classList.remove("active");
    mediumZone.classList.remove("active");
    highZone.classList.remove("active");

    lowZone.style.opacity = "0.4";
    mediumZone.style.opacity = "0.4";
    highZone.style.opacity = "0.4";

    if (level === "GREEN") {
        lowZone.classList.add("active");
        lowZone.style.opacity = "1";
    } else if (level === "YELLOW" || level === "ORANGE") {
        mediumZone.classList.add("active");
        mediumZone.style.opacity = "1";
    } else if (level === "RED") {
        highZone.classList.add("active");
        highZone.style.opacity = "1";
    }
}

function updateAlerts(data) {
    const alertList = document.getElementById("alertList");
    if (!alertList) return;

    if (data.alertLevel === "RED") {
        alertList.innerHTML = `
            <li>🚨 Critical crowd density detected in ${data.zoneName}</li>
            <li>⚠ Emergency mode is active</li>
        `;
    } else if (data.alertLevel === "ORANGE") {
        alertList.innerHTML = `
            <li>⚠ High crowd density in ${data.zoneName}</li>
            <li>🚨 Situation needs attention</li>
        `;
    } else if (data.alertLevel === "YELLOW") {
        alertList.innerHTML = `
            <li>⚠ Moderate crowd density in ${data.zoneName}</li>
            <li>✅ Situation under control</li>
        `;
    } else {
        alertList.innerHTML = `
            <li>✅ Crowd level is low in ${data.zoneName}</li>
            <li>🟢 Area is safe</li>
        `;
    }
}

function loadLiveMetrics() {
    fetch("http://localhost:8080/crowd/all")
        .then(res => {
            console.log("Metrics fetch response:", res.status);

            if (!res.ok) {
                throw new Error("Failed to fetch crowd metrics");
            }

            return res.json();
        })
        .then(data => {
            console.log("Crowd metrics data:", data);

            if (!data || data.length === 0) return;

            const latest = data[data.length - 1];

            const crowdCount = document.getElementById("crowdCount");
            const densityLevel = document.getElementById("densityLevel");
            const anomalyScore = document.getElementById("anomalyScore");
            const zoneName = document.getElementById("zoneName");
            const activeAlerts = document.getElementById("activeAlerts");
            const lastUpdated = document.getElementById("lastUpdated");
            const emergencyStatus = document.getElementById("emergencyStatus");

            if (crowdCount) {
                crowdCount.innerText = latest.estimatedHeads ?? 0;
            }

            if (densityLevel) {
                densityLevel.innerText = latest.alertLevel ?? "UNKNOWN";
            }

            if (zoneName) {
                zoneName.innerText = latest.zoneName ?? "Zone A";
            }

            if (anomalyScore) {
                anomalyScore.innerText = latest.density
                    ? (latest.density * 100).toFixed(2) + "%"
                    : "0%";
            }

            if (lastUpdated) {
                lastUpdated.innerText = latest.timestamp ?? "No timestamp";
            }

            updateHeatmap(latest.alertLevel);

            if (latest.alertLevel === "RED" && emergencyState === "OFF") {
                emergencyState = "ON";
            } else if (
                (latest.alertLevel === "GREEN" ||
                 latest.alertLevel === "YELLOW" ||
                 latest.alertLevel === "ORANGE") &&
                emergencyState === "ON"
            ) {
                emergencyState = "OFF";
            }

            if (activeAlerts) {
                activeAlerts.innerText =
                    (latest.alertLevel === "RED" || latest.alertLevel === "ORANGE") ? "1" : "0";
            }

            if (emergencyStatus) {
                emergencyStatus.innerText = latest.emergencyTriggered ? "ON" : emergencyState;
            }

            updateAlerts(latest);
        })
        .catch(err => {
            console.error("Live metrics error:", err);
        });
}

function triggerEmergency(showAlert = true) {
    emergencyState = "ON";

    const emergencyStatus = document.getElementById("emergencyStatus");
    if (emergencyStatus) {
        emergencyStatus.innerText = "ON";
    }

    if (showAlert) {
        alert("Emergency turned ON");
    }
}

function resetEmergency(showAlert = true) {
    emergencyState = "OFF";

    const emergencyStatus = document.getElementById("emergencyStatus");
    if (emergencyStatus) {
        emergencyStatus.innerText = "OFF";
    }

    if (showAlert) {
        alert("Emergency turned OFF");
    }
}

function startCamera() {
    const video = document.getElementById("liveVideo");

    if (!video) {
        console.error("liveVideo element not found");
        return;
    }

    navigator.mediaDevices.getUserMedia({ video: true })
        .then(function (stream) {
            video.srcObject = stream;
            video.play();
            console.log("Camera started successfully");
        })
        .catch(function (error) {
            console.error("Camera error:", error);
            alert("Camera access failed. Please allow camera permission.");
        });
}

function logout() {
    window.location.href = "login.html";
}

window.onload = function () {
    console.log("New dashboard JS loaded");
    loadUsers();
    loadLiveMetrics();
    setInterval(loadLiveMetrics, 3000);
    startCamera();
};