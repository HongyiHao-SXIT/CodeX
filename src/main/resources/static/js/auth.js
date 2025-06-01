document.addEventListener('DOMContentLoaded', function() {
    // 密码强度检测
    const passwordInput = document.getElementById('password');
    if (passwordInput) {
        const strengthBar = document.getElementById('passwordStrength');
        
        passwordInput.addEventListener('input', function() {
            const password = this.value;
            let strength = 0;
            
            if (password.length > 0) strength += 20;
            if (password.length >= 8) strength += 20;
            if (/[A-Z]/.test(password)) strength += 20;
            if (/[0-9]/.test(password)) strength += 20;
            if (/[^A-Za-z0-9]/.test(password)) strength += 20;
            
            strengthBar.style.width = strength + '%';
            
            if (strength < 40) {
                strengthBar.style.backgroundColor = 'var(--danger-color)';
            } else if (strength < 80) {
                strengthBar.style.backgroundColor = 'var(--warning-color)';
            } else {
                strengthBar.style.backgroundColor = 'var(--success-color)';
            }
        });
    }
    
    // 注册表单验证
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            const password = document.getElementById('password')?.value;
            const confirmPassword = document.getElementById('confirmPassword')?.value;
            
            if (password && confirmPassword && password !== confirmPassword) {
                e.preventDefault();
                alert('两次输入的密码不一致');
                return false;
            }
            
            const agreeTerms = document.getElementById('agreeTerms');
            if (agreeTerms && !agreeTerms.checked) {
                e.preventDefault();
                alert('请同意服务条款和隐私政策');
                return false;
            }
            
            return true;
        });
    }
});