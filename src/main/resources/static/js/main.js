// main.js

document.addEventListener("DOMContentLoaded", function () {

    // Handle login form validation
    const loginForm = document.getElementById("loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", function (e) {
            const username = document.getElementById("username").value.trim();
            const password = document.getElementById("password").value.trim();
            if (!username || !password) {
                e.preventDefault();
                showAlert("Please enter both username and password.", "danger");
            }
        });
    }

    // Handle register form validation
    const registerForm = document.getElementById("registerForm");
    if (registerForm) {
        registerForm.addEventListener("submit", function (e) {
            const username = document.getElementById("username").value.trim();
            const email = document.getElementById("email").value.trim();
            const password = document.getElementById("password").value.trim();
            const role = document.getElementById("role").value;

            if (!username || !email || !password || !role) {
                e.preventDefault();
                showAlert("Please fill in all fields to register.", "warning");
            }
        });
    }

    // Show alert dynamically
    function showAlert(message, type) {
        const alertPlaceholder = document.getElementById("alertPlaceholder");
        if (alertPlaceholder) {
            alertPlaceholder.innerHTML = `
                <div class="alert alert-${type} alert-dismissible fade show" role="alert">
                    ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `;
        }
    }

    // Fade in content
    const containers = document.querySelectorAll(".container");
    containers.forEach(container => {
        container.style.opacity = 0;
        container.style.transition = "opacity 0.6s ease-in-out";
        setTimeout(() => container.style.opacity = 1, 100);
    });

});
