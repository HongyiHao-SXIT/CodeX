function register() {
    const loginAccount = document.getElementById('loginAccount').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const name = document.getElementById('name').value;
    const idCard = document.getElementById('idCard').value;
    const birthDate = document.getElementById('birthDate').value;
    const address = document.getElementById('address').value;
    const postcode = document.getElementById('postcode').value;
    const email = document.getElementById('email').value;

    if (!/^[a-zA-Z][a-zA-Z0-9]*$/.test(loginAccount)) {
        alert('The login account should consist of letters and numbers, and start with a letter.');
        document.getElementById('loginAccount').focus();
        return;
    }

    if (password.length < 8 ||!/[a-zA-Z]/.test(password) ||!/\d/.test(password) ||!/[^a-zA-Z0-9]/.test(password)) {
        alert('The password should be at least 8 characters long and contain letters, numbers, and characters other than letters and numbers.');
        document.getElementById('password').focus();
        return;
    }

    if (password!== confirmPassword) {
        alert('The passwords entered twice do not match.');
        document.getElementById('confirmPassword').focus();
        return;
    }

    if (name === '') {
        alert('Please enter your name.');
        document.getElementById('name').focus();
        return;
    }

    if (!/^\d{17}[\dXx]$/.test(idCard)) {
        alert('Please enter a valid 18-digit ID card number.');
        document.getElementById('idCard').focus();
        return;
    }

    if (!/^\d{4}-\d{2}-\d{2}$/.test(birthDate)) {
        alert('The date of birth should be in the format yyyy-mm-dd.');
        document.getElementById('birthDate').focus();
        return;
    }
    const birthYear = parseInt(birthDate.split('-')[0]);
    const idCardYear = parseInt(idCard.slice(6, 10));
    if (birthYear!== idCardYear) {
        alert('According to the date of birth, the ID card number is invalid.');
        document.getElementById('idCard').focus();
        return;
    }

    if (address === '') {
        alert('Please enter your address.');
        document.getElementById('address').focus();
        return;
    }

    if (!/^\d{6}$/.test(postcode)) {
        alert('The postcode should be a 6-digit number.');
        document.getElementById('postcode').focus();
        return;
    }

    if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)) {
        alert('Please enter a valid email address.');
        document.getElementById('email').focus();
        return;
    }

    alert('Registration successful!');

    document.getElementById('loginAccount').value = '';
    document.getElementById('password').value = '';
    document.getElementById('confirmPassword').value = '';
    document.getElementById('name').value = '';
    document.getElementById('idCard').value = '';
    document.getElementById('birthDate').value = '';
    document.getElementById('address').value = '';
    document.getElementById('postcode').value = '';
    document.getElementById('email').value = '';
}