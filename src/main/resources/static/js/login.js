document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("loginForm");
    const username = document.getElementById("username");
    const password = document.getElementById("password");
    const errorMessage = document.getElementById("error-message");

    form.addEventListener("submit", function (e) {
        if (username.value.trim() === "" || password.value.trim() === "") {
            e.preventDefault();
            errorMessage.textContent = "Username and Password are required.";
        }
    });
});
