document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止表单默认提交行为

    // 获取输入框元素
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('email');

    // 清空输入框的值
    nameInput.value = '';
    emailInput.value = '';

    // 这里可以添加表单提交的逻辑，比如使用 AJAX 提交表单数据
});