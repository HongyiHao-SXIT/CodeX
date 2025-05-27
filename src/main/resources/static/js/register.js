document.addEventListener("DOMContentLoaded", function () {
    const showPasswordCheckbox = document.getElementById("show-password");
    const passwordInput = document.getElementById("password");

    showPasswordCheckbox.addEventListener("change", function () {
        passwordInput.type = this.checked ? "text" : "password";
    });
});
