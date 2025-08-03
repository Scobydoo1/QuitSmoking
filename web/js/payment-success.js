/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Payment Success JavaScript - NetBeans 8.3 & JDK 1.8 Compatible

// Initialize when document ready
if (document.addEventListener) {
    document.addEventListener('DOMContentLoaded', function() {
        initializePaymentSuccess();
    });
} else if (document.attachEvent) {
    document.attachEvent('onreadystatechange', function() {
        if (document.readyState === 'complete') {
            initializePaymentSuccess();
        }
    });
}

function initializePaymentSuccess() {
    // Show success message
    setTimeout(function() {
        showSuccessToast();
    }, 1000);
    
    // Auto redirect after 30 seconds
    setTimeout(function() {
        if (confirm('Bạn có muốn quay lại trang chủ không?')) {
            window.location.href = 'homepage.jsp';
        }
    }, 30000);
}

function showSuccessToast() {
    var toast = document.createElement('div');
    toast.innerHTML = 
        '<div style="display: flex; align-items: center; gap: 10px;">' +
            '<i class="fas fa-check-circle"></i>' +
            '<span>Chào mừng bạn đến với chương trình cai thuốc!</span>' +
        '</div>';
    
    toast.style.position = 'fixed';
    toast.style.top = '20px';
    toast.style.right = '20px';
    toast.style.background = '#4caf50';
    toast.style.color = 'white';
    toast.style.padding = '15px 20px';
    toast.style.borderRadius = '10px';
    toast.style.zIndex = '9999';
    toast.style.boxShadow = '0 6px 20px rgba(76, 175, 80, 0.3)';
    toast.style.animation = 'slideInRight 0.5s ease-out';
    
    document.body.appendChild(toast);
    
    setTimeout(function() {
        if (toast.parentNode) {
            toast.style.animation = 'slideOutRight 0.5s ease-out';
            setTimeout(function() {
                if (toast.parentNode) {
                    toast.parentNode.removeChild(toast);
                }
            }, 500);
        }
    }, 4000);
}

// Add CSS animations
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
