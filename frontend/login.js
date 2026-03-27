function handleRoleChange() {
    const role = document.getElementById("role").value;
    const passwordGroup = document.getElementById("passwordGroup");
    const clientHint = document.getElementById("clientHint");
    const passwordInput = document.getElementById("password");
    const message = document.getElementById("message");

    message.innerText = "";
    message.className = "message-box";

    if (role === "client") {
        passwordGroup.style.display = "block";
        clientHint.innerText = "First-time client can continue with email/mobile. Returning client should enter password.";
        passwordInput.placeholder = "Enter password (for returning client)";
    } else if (role === "admin") {
        passwordGroup.style.display = "block";
        clientHint.innerText = "Admin must login using username and password.";
        passwordInput.placeholder = "Enter admin password";
    } else {
        passwordGroup.style.display = "block";
        clientHint.innerText = "Select a role to continue.";
        passwordInput.placeholder = "Enter password";
    }
}

function togglePassword() {
    const passwordInput = document.getElementById("password");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}

document.getElementById("loginForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const role = document.getElementById("role").value;
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const message = document.getElementById("message");

    message.innerText = "";
    message.className = "message-box";

    if (role === "" || username === "") {
        message.innerText = "Please select role and fill required details.";
        message.classList.add("error");
        return;
    }

    if (role === "admin") {
        if (username === "admin" && password === "admin123") {
            message.innerText = "Admin login successful.";
            message.classList.add("success");

            setTimeout(() => {
                window.location.href = "admin-dashboard.html";
            }, 800);
        } else {
            message.innerText = "Invalid admin credentials.";
            message.classList.add("error");
        }
    } 
    
    else if (role === "client") {
        const existingClient = localStorage.getItem("client_" + username);

        if (existingClient) {
            if (password === existingClient) {
                message.innerText = "Client login successful.";
                message.classList.add("success");

                setTimeout(() => {
                    window.location.href = "client-dashboard.html";
                }, 800);
            } else {
                message.innerText = "Wrong password for existing client.";
                message.classList.add("error");
            }
        } else {
            localStorage.setItem("client_" + username, "client123");
            message.innerText = "First-time client detected. Demo account created. Use password: client123 next time.";
            message.classList.add("success");

            setTimeout(() => {
                window.location.href = "client-dashboard.html";
            }, 1300);
        }
    }
});