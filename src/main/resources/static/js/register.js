document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registerForm');
    
    // Form submission validation
    form.addEventListener('submit', function(event) {
        if (!validateForm()) {
            event.preventDefault();
        }
    });
    
    // Real-time validation
    document.getElementById('account').addEventListener('blur', validateAccount);
    document.getElementById('password').addEventListener('blur', validatePassword);
    document.getElementById('confirmPassword').addEventListener('blur', validateConfirmPassword);
    document.getElementById('username').addEventListener('blur', validateUsername);
    document.getElementById('userno').addEventListener('blur', validateUserno);
    document.getElementById('email').addEventListener('blur', validateEmail);
    document.getElementById('classno').addEventListener('blur', validateClassno);
    
    // Validation functions
    function validateForm() {
        let isValid = true;
        
        isValid = validateAccount() && isValid;
        isValid = validatePassword() && isValid;
        isValid = validateConfirmPassword() && isValid;
        isValid = validateUsername() && isValid;
        isValid = validateUserno() && isValid;
        isValid = validateEmail() && isValid;
        isValid = validateClassno() && isValid;
        
        return isValid;
    }
    
    function validateAccount() {
        const account = document.getElementById('account');
        const error = document.getElementById('accountError');
        const regex = /^[a-zA-Z0-9_]{4,20}$/;
        
        if (!account.value.trim()) {
            showError(error, 'Username is required');
            return false;
        }
        
        if (!regex.test(account.value)) {
            showError(error, 'Username must be 4-20 characters (letters, numbers, underscore)');
            return false;
        }
        
        hideError(error);
        return true;
    }
    
    function validatePassword() {
        const password = document.getElementById('password');
        const error = document.getElementById('passwordError');
        const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        
        if (!password.value.trim()) {
            showError(error, 'Password is required');
            return false;
        }
        
        if (!regex.test(password.value)) {
            showError(error, 'Password must be at least 8 characters with letters and numbers');
            return false;
        }
        
        hideError(error);
        return true;
    }
    
    function validateConfirmPassword() {
        const password = document.getElementById('password');
        const confirmPassword = document.getElementById('confirmPassword');
        const error = document.getElementById('confirmPasswordError');
        
        if (!confirmPassword.value.trim()) {
            showError(error, 'Please confirm your password');
            return false;
        }
        
        if (password.value !== confirmPassword.value) {
            showError(error, 'Passwords do not match');
            return false;
        }
        
        hideError(error);
        return true;
    }
    
    function validateUsername() {
        const username = document.getElementById('username');
        const error = document.getElementById('usernameError');
        
        if (!username.value.trim()) {
            showError(error, 'Full name is required');
            return false;
        }
        
        if (username.value.length < 2 || username.value.length > 20) {
            showError(error, 'Name must be between 2-20 characters');
            return false;
        }
        
        hideError(error);
        return true;
    }
    
    function validateUserno() {
        const userno = document.getElementById('userno');
        const error = document.getElementById('usernoError');
        const regex = /^[0-9]{10}$/;
        
        if (!userno.value.trim()) {
            showError(error, 'Student/Staff ID is required');
            return false;
        }
        
        if (!regex.test(userno.value)) {
            showError(error, 'ID must be 10 digits');
            return false;
        }
        
        hideError(error);
        return true;
    }
    
    function validateEmail() {
        const email = document.getElementById('email');
        const error = document.getElementById('emailError');
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        
        if (!email.value.trim()) {
            showError(error, 'Email is required');
            return false;
        }
        
        if (!regex.test(email.value)) {
            showError(error, 'Please enter a valid email address');
            return false;
        }
        
        hideError(error);
        return true;
    }
    
    function validateClassno() {
        const classno = document.getElementById('classno');
        const error = document.getElementById('classnoError');
        
        if (!classno.value.trim()) {
            showError(error, 'Class is required');
            return false;
        }
        
        if (classno.value.length > 20) {
            showError(error, 'Class name is too long');
            return false;
        }
        
        hideError(error);
        return true;
    }
    
    function showError(element, message) {
        element.textContent = message;
        element.style.display = 'block';
    }
    
    function hideError(element) {
        element.textContent = '';
        element.style.display = 'none';
    }
});