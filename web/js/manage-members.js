/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Manage Members JavaScript - NetBeans 8.3 & JDK 1.8 Compatible

// Initialize when document ready
if (document.addEventListener) {
    document.addEventListener('DOMContentLoaded', function() {
        initializeManageMembers();
    });
} else if (document.attachEvent) {
    document.attachEvent('onreadystatechange', function() {
        if (document.readyState === 'complete') {
            initializeManageMembers();
        }
    });
}

function initializeManageMembers() {
    // Initialize search functionality
    initializeSearch();
    
    // Initialize filter functionality
    initializeFilters();
    
    // Initialize select all functionality
    initializeSelectAll();
}

function initializeSearch() {
    var searchInput = document.getElementById('searchInput');
    if (searchInput) {
        if (searchInput.addEventListener) {
            searchInput.addEventListener('input', function() {
                searchMembers();
            });
        }
    }
}

function searchMembers() {
    var input = document.getElementById('searchInput');
    var filter = input.value.toLowerCase();
    var table = document.getElementById('membersTable');
    var rows = table.getElementsByTagName('tr');
    
    for (var i = 1; i < rows.length; i++) {
        var row = rows[i];
        var cells = row.getElementsByTagName('td');
        var found = false;
        
        for (var j = 0; j < cells.length; j++) {
            var cell = cells[j];
            if (cell) {
                var textValue = cell.textContent || cell.innerText;
                if (textValue.toLowerCase().indexOf(filter) > -1) {
                    found = true;
                    break;
                }
            }
        }
        
        if (found) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    }
    
    updateSearchResults();
}

function initializeFilters() {
    var filterBtns = document.querySelectorAll('.filter-btn');
    
    for (var i = 0; i < filterBtns.length; i++) {
        if (filterBtns[i].addEventListener) {
            filterBtns[i].addEventListener('click', function() {
                var filter = this.getAttribute('data-filter');
                filterMembers(filter);
                
                // Update active button
                var allBtns = document.querySelectorAll('.filter-btn');
                for (var j = 0; j < allBtns.length; j++) {
                    allBtns[j].className = allBtns[j].className.replace(' active', '');
                }
                this.className += ' active';
            });
        }
    }
}

function filterMembers(filter) {
    var rows = document.querySelectorAll('.member-row');
    
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var status = row.getAttribute('data-status');
        
        if (filter === 'all' || status === filter) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    }
    
    updateSearchResults();
}

function initializeSelectAll() {
    var selectAllCheckbox = document.getElementById('selectAll');
    if (selectAllCheckbox) {
        if (selectAllCheckbox.addEventListener) {
            selectAllCheckbox.addEventListener('change', function() {
                toggleSelectAll();
            });
        }
    }
}

function toggleSelectAll() {
    var selectAllCheckbox = document.getElementById('selectAll');
    var memberCheckboxes = document.querySelectorAll('.member-checkbox');
    
    for (var i = 0; i < memberCheckboxes.length; i++) {
        memberCheckboxes[i].checked = selectAllCheckbox.checked;
    }
}

function updateSearchResults() {
    var visibleRows = 0;
    var rows = document.querySelectorAll('.member-row');
    
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].style.display !== 'none') {
            visibleRows++;
        }
    }
    
    var currentCountElement = document.querySelector('.current-count');
    if (currentCountElement) {
        currentCountElement.textContent = visibleRows;
    }
}

function refreshData() {
    // Show loading state
    var btn = event.target;
    var originalContent = btn.innerHTML;
    btn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Đang tải...';
    btn.disabled = true;
    
    // Simulate refresh
    setTimeout(function() {
        btn.innerHTML = originalContent;
        btn.disabled = false;
        showToast('Dữ liệu đã được làm mới', 'success');
        
        // Reload page
        window.location.reload();
    }, 1500);
}

function exportData() {
    // Show loading state
    var btn = event.target;
    var originalContent = btn.innerHTML;
    btn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Đang xuất...';
    btn.disabled = true;
    
    // Simulate export
    setTimeout(function() {
        btn.innerHTML = originalContent;
        btn.disabled = false;
        showToast('Dữ liệu đã được xuất thành công', 'success');
    }, 2000);
}

function showToast(message, type) {
    var toast = document.createElement('div');
    toast.className = 'toast toast-' + type;
    
    var icon = type === 'success' ? 'check-circle' : 'info-circle';
    var color = type === 'success' ? '#27ae60' : '#3498db';
    
    toast.innerHTML = 
        '<div style="display: flex; align-items: center; gap: 8px;">' +
            '<i class="fas fa-' + icon + '"></i>' +
            '<span>' + message + '</span>' +
        '</div>';
    
    toast.style.position = 'fixed';
    toast.style.top = '20px';
    toast.style.right = '20px';
    toast.style.background = color;
    toast.style.color = 'white';
    toast.style.padding = '12px 20px';
    toast.style.borderRadius = '8px';
    toast.style.zIndex = '9999';
    toast.style.boxShadow = '0 4px 12px rgba(0,0,0,0.15)';
    toast.style.animation = 'slideInRight 0.3s ease-out';
    
    document.body.appendChild(toast);
    
    setTimeout(function() {
        if (toast.parentNode) {
            toast.style.animation = 'slideOutRight 0.3s ease-out';
            setTimeout(function() {
                if (toast.parentNode) {
                    toast.parentNode.removeChild(toast);
                }
            }, 300);
        }
    }, 3000);
}

// Add CSS animations for toast
var style = document.createElement('style');
style.textContent = 
    '@keyframes slideInRight {' +
        'from { opacity: 0; transform: translateX(100%); }' +
        'to { opacity: 1; transform: translateX(0); }' +
    '}' +
    '@keyframes slideOutRight {' +
        'from { opacity: 1; transform: translateX(0); }' +
        'to { opacity: 0; transform: translateX(100%); }' +
    '}';
document.head.appendChild(style);
