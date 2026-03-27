function loadClientDashboardData() {

    // Dummy data
    document.getElementById("zoneName").innerText = "Zone B";
    document.getElementById("crowdLevel").innerText = "Moderate";
    document.getElementById("safetyStatus").innerText = "Stable";

    document.getElementById("peopleCount").innerText = "74";
    document.getElementById("densityValue").innerText = "Medium";
    document.getElementById("riskValue").innerText = "Low";

    // REAL backend connection
    fetch("http://localhost:8080/client/emergency-status")
        .then(res => res.text())
        .then(status => {
            const banner = document.getElementById("emergencyBanner");

            if (status === "ON") {
                banner.classList.remove("hidden");
            } else {
                banner.classList.add("hidden");
            }
        })
        .catch(err => console.error("Error:", err));
}

function logout() {
    window.location.href = "login.html";
}

window.onload = loadClientDashboardData;