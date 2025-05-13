// 登录逻辑
document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');

    if (loginForm) {
        loginForm.addEventListener('submit', function (e) {
            e.preventDefault();
            const username = document.getElementById('login-username').value;
            const password = document.getElementById('login-password').value;

            fetch('/api/login', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({username, password})
            })
            .then(response => {
                if (response.ok) {
                    alert('登录成功');
                    window.location.href = '/dashboard'; // 登录成功后的页面
                } else {
                    alert('登录失败');
                }
            });
        });
    }

    if (registerForm) {
        registerForm.addEventListener('submit', function (e) {
            e.preventDefault();
            const username = document.getElementById('register-username').value;
            const password = document.getElementById('register-password').value;
            const confirmPassword = document.getElementById('register-confirm-password').value;

            if (password !== confirmPassword) {
                alert('两次输入密码不一致');
                return;
            }

            fetch('/api/register', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({username, password})
            })
            .then(response => {
                if (response.ok) {
                    alert('注册成功');
                    window.location.href = '/login.html';
                } else {
                    alert('注册失败');
                }
            });
        });
    }
});
