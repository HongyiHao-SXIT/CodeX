document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.focus();
    }
    
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
    
    const advancedSearchToggle = document.getElementById('advancedSearchToggle');
    if (advancedSearchToggle) {
        advancedSearchToggle.addEventListener('click', function(e) {
            e.preventDefault();
            const advancedSearch = document.getElementById('advancedSearch');
            advancedSearch.classList.toggle('d-none');
            this.textContent = advancedSearch.classList.contains('d-none') ? 
                'Show Advanced Search' : 'Hide Advanced Search';
        });
    }
    
    const savePaperBtns = document.querySelectorAll('.save-paper-btn');
    savePaperBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const paperId = this.dataset.paperId;
            const isSaved = this.classList.toggle('active');
            
            console.log(isSaved ? 'Saved paper:' : 'Unsaved paper:', paperId);

            const icon = this.querySelector('i');
            if (isSaved) {
                icon.classList.remove('bi-bookmark');
                icon.classList.add('bi-bookmark-check-fill');
                this.querySelector('.btn-text').textContent = 'Saved';
            } else {
                icon.classList.remove('bi-bookmark-check-fill');
                icon.classList.add('bi-bookmark');
                this.querySelector('.btn-text').textContent = 'Save';
            }
        });
    });
});