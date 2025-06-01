document.addEventListener('DOMContentLoaded', function() {
    // 搜索框自动聚焦
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.focus();
    }
    
    // 搜索标签点击效果
    const searchTags = document.querySelectorAll('.search-tag');
    searchTags.forEach(tag => {
        tag.addEventListener('click', function(e) {
            e.preventDefault();
            const searchText = this.textContent.trim();
            if (searchInput) {
                searchInput.value = searchText;
                searchInput.focus();
            }
        });
    });
    
    // 高级搜索切换
    const advancedSearchToggle = document.getElementById('advancedSearchToggle');
    if (advancedSearchToggle) {
        advancedSearchToggle.addEventListener('click', function(e) {
            e.preventDefault();
            const advancedSearch = document.getElementById('advancedSearch');
            advancedSearch.classList.toggle('d-none');
            this.textContent = advancedSearch.classList.contains('d-none') ? 
                '显示高级搜索选项' : '隐藏高级搜索选项';
        });
    }
    
    // 论文收藏功能
    const savePaperBtns = document.querySelectorAll('.save-paper-btn');
    savePaperBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const paperId = this.dataset.paperId;
            const isSaved = this.classList.toggle('active');
            
            // 这里可以添加AJAX请求来保存/取消保存论文
            console.log(isSaved ? '保存论文:' : '取消保存:', paperId);
            
            // 更新按钮状态
            const icon = this.querySelector('i');
            if (isSaved) {
                icon.classList.remove('bi-bookmark');
                icon.classList.add('bi-bookmark-check-fill');
                this.querySelector('.btn-text').textContent = '已收藏';
            } else {
                icon.classList.remove('bi-bookmark-check-fill');
                icon.classList.add('bi-bookmark');
                this.querySelector('.btn-text').textContent = '收藏';
            }
        });
    });
});