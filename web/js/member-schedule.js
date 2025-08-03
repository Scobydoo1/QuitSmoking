/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Schedule JavaScript - NetBeans 8.3 & JDK 1.8 Compatible

// Initialize when document ready
if (document.addEventListener) {
    document.addEventListener('DOMContentLoaded', function() {
        initializeSchedule();
    });
} else if (document.attachEvent) {
    document.attachEvent('onreadystatechange', function() {
        if (document.readyState === 'complete') {
            initializeSchedule();
        }
    });
}

function initializeSchedule() {
    // Initialize form validation
    initializeFormValidation();
    
    // Initialize auto-refresh
    initializeAutoRefresh();
}

function initializeFormValidation() {
    var forms = document.querySelectorAll('.update-form');
    
    for (var i = 0; i < forms.length; i++) {
        if (forms[i].addEventListener) {
            forms[i].addEventListener('submit', function(event) {
                return validateUpdateForm(event);
            });
        }
    }
}

function validateUpdateForm(event) {
    var form = event.target;
    var meetLink = form.querySelector('input[name="meetLink"]');
    
    if (meetLink && meetLink.value) {
        var urlPattern = /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/;
        if (!urlPattern.test(meetLink.value)) {
            event.preventDefault();
            alert('Vui lòng nhập link hợp lệ!');
            return false;
        }
    }
    
    // Show loading state
    var submitBtn = form.querySelector('button[type="submit"]');
    if (submitBtn) {
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Đang cập nhật...';
        submitBtn.disabled = true;
    }
    
    return true;
}

function initializeAutoRefresh() {
    // Auto refresh every 5 minutes
    setInterval(function() {
        if (confirm('Bạn có muốn làm mới trang để cập nhật lịch mới nhất không?')) {
            window.location.reload();
        }
    }, 300000); // 5 minutes
}

// Utility functions
function showToast(message, type) {
    var toast = document.createElement('div');
    toast.className = 'toast toast-' + type;
    
    var icon = type === 'success' ? 'check-circle' : 'info-circle';
    var color = type === 'success' ? '#27ae60' : '#17a2b8';
    
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
