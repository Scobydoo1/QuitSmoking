/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Progress Log JavaScript - NetBeans 8.3 & JDK 1.8 Compatible

// Global variables
var selectedCigaretteCount = null;
var isFormSubmitting = false;

// Initialize when document ready
if (document.addEventListener) {
    document.addEventListener('DOMContentLoaded', function() {
        initializeProgressLog();
    });
} else if (document.attachEvent) {
    document.attachEvent('onreadystatechange', function() {
        if (document.readyState === 'complete') {
            initializeProgressLog();
        }
    });
}

function initializeProgressLog() {
    // Initialize form validation
    var form = document.getElementById('progressForm');
    if (form) {
        if (form.addEventListener) {
            form.addEventListener('submit', function(event) {
                return validateForm(event);
            });
        } else if (form.attachEvent) {
            form.attachEvent('onsubmit', function(event) {
                return validateForm(event);
            });
        }
    }
    
    // Initialize auto-resize for textareas
    var textareas = document.querySelectorAll('textarea');
    for (var i = 0; i < textareas.length; i++) {
        if (textareas[i].addEventListener) {
            textareas[i].addEventListener('input', function() {
                autoResizeTextarea(this);
            });
        } else if (textareas[i].attachEvent) {
            textareas[i].attachEvent('oninput', function() {
                autoResizeTextarea(this);
            });
        }
    }
    
    // Initialize cigarette input validation
    var cigaretteInput = document.getElementById('cigarettes');
    if (cigaretteInput) {
        if (cigaretteInput.addEventListener) {
            cigaretteInput.addEventListener('input', function() {
                validateCigaretteInput(this);
            });
        }
    }
}

function setCigaretteCount(count) {
    var input = document.getElementById('cigarettes');
    if (!input) return;
    
    input.value = count;
    selectedCigaretteCount = count;
    
    // Visual feedback cho input
    input.style.transform = 'scale(1.1)';
    input.style.background = 'linear-gradient(135deg, #c8e6c9, #a5d6a7)';
    input.style.color = '#1b5e20';
    input.style.borderColor = '#2e7d32';
    
    // Reset tất cả buttons
    var buttons = document.querySelectorAll('.quick-btn');
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].className = buttons[i].className.replace(' selected', '');
        buttons[i].style.background = 'linear-gradient(135deg, #e8f5e8 0%, #c8e6c9 100%)';
        buttons[i].style.color = '#2e7d32';
    }
    
    // Highlight button được chọn
    if (window.event && window.event.target) {
        var clickedButton = window.event.target;
        clickedButton.className += ' selected';
        clickedButton.style.background = 'linear-gradient(135deg, #4caf50 0%, #66bb6a 100%)';
        clickedButton.style.color = 'white';
    }
    
    // Reset input style sau animation
    setTimeout(function() {
        input.style.transform = 'scale(1)';
        input.style.background = '';
        input.style.color = '';
        input.style.borderColor = '';
    }, 300);
    
    // Show success feedback
    showToast('Đã chọn ' + count + ' điếu thuốc', 'success');
}

function validateCigaretteInput(input) {
    var value = parseInt(input.value);
    
    if (isNaN(value) || value < 0) {
        input.style.borderColor = '#f44336';
        showToast('Vui lòng nhập số điếu thuốc hợp lệ (≥ 0)', 'error');
        return false;
    } else if (value > 100) {
        input.style.borderColor = '#ff9800';
        showToast('Số điếu thuốc có vẻ quá cao. Bạn có chắc chắn?', 'warning');
        return true;
    } else {
        input.style.borderColor = '#4caf50';
        return true;
    }
}

function autoResizeTextarea(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
}

function validateForm(event) {
    if (isFormSubmitting) {
        event.preventDefault();
        return false;
    }
    
    var isValid = true;
    var errorMessages = [];
    
    // Validate cigarette count
    var cigaretteInput = document.getElementById('cigarettes');
    var cigaretteValue = parseInt(cigaretteInput.value);
    
    if (!cigaretteInput.value || isNaN(cigaretteValue) || cigaretteValue < 0) {
        errorMessages.push('Vui lòng nhập số điếu thuốc hợp lệ!');
        isValid = false;
        cigaretteInput.focus();
    }
    
    // Validate all textareas - CHỈ KIỂM TRA RỖNG, KHÔNG KIỂM TRA ĐỘ DÀI
    var textareas = document.querySelectorAll('textarea[required]');
    for (var i = 0; i < textareas.length; i++) {
        var textarea = textareas[i];
        if (!textarea.value.trim()) {
            errorMessages.push('Vui lòng trả lời câu hỏi ' + (i + 1) + '!');
            isValid = false;
            if (errorMessages.length === 1) {
                textarea.focus();
                textarea.scrollIntoView({ behavior: 'smooth', block: 'center' });
            }
        }
        // ĐÃ XÓA PHẦN KIỂM TRA ĐỘ DÀI TỐI THIỂU
    }
    
    if (!isValid) {
        event.preventDefault();
        showToast(errorMessages.join('\n'), 'error');
        return false;
    }
    
    // Show loading state
    isFormSubmitting = true;
    var submitBtn = document.querySelector('.submit-btn');
    if (submitBtn) {
        submitBtn.className += ' loading';
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Đang gửi...';
        submitBtn.disabled = true;
    }
    
    // Show success message
    showToast('Đang gửi tiến trình của bạn...', 'info');
    
    return true;
}

function showToast(message, type) {
    // Remove existing toasts
    var existingToasts = document.querySelectorAll('.toast');
    for (var i = 0; i < existingToasts.length; i++) {
        if (existingToasts[i].parentNode) {
            existingToasts[i].parentNode.removeChild(existingToasts[i]);
        }
    }
    
    var toast = document.createElement('div');
    toast.className = 'toast toast-' + type;
    
    var icon = 'info-circle';
    var color = '#3498db';
    
    if (type === 'success') {
        icon = 'check-circle';
        color = '#4caf50';
    } else if (type === 'error') {
        icon = 'exclamation-circle';
        color = '#f44336';
    } else if (type === 'warning') {
        icon = 'exclamation-triangle';
        color = '#ff9800';
    }
    
    toast.innerHTML = 
        '<div style="display: flex; align-items: center; gap: 10px;">' +
            '<i class="fas fa-' + icon + '"></i>' +
            '<span>' + message + '</span>' +
        '</div>';
    
    // Toast styles
    toast.style.position = 'fixed';
    toast.style.top = '20px';
    toast.style.right = '20px';
    toast.style.background = color;
    toast.style.color = 'white';
    toast.style.padding = '15px 20px';
    toast.style.borderRadius = '10px';
    toast.style.zIndex = '9999';
    toast.style.boxShadow = '0 6px 20px rgba(0,0,0,0.15)';
    toast.style.maxWidth = '400px';
    toast.style.whiteSpace = 'pre-line';
    toast.style.fontWeight = '500';
    toast.style.fontSize = '14px';
    toast.style.animation = 'slideInRight 0.3s ease-out';
    
    document.body.appendChild(toast);
    
    // Auto remove toast
    setTimeout(function() {
        if (toast.parentNode) {
            toast.style.animation = 'slideOutRight 0.3s ease-out';
            setTimeout(function() {
                if (toast.parentNode) {
                    toast.parentNode.removeChild(toast);
                }
            }, 300);
        }
    }, type === 'error' ? 5000 : 3000);
}

// Utility functions
function smoothScrollTo(element) {
    if (element && element.scrollIntoView) {
        element.scrollIntoView({
            behavior: 'smooth',
            block: 'center'
        });
    }
}

function resetForm() {
    var form = document.getElementById('progressForm');
    if (form) {
        form.reset();
        
        // Reset quick select buttons
        var buttons = document.querySelectorAll('.quick-btn');
        for (var i = 0; i < buttons.length; i++) {
            buttons[i].className = buttons[i].className.replace(' selected', '');
            buttons[i].style.background = '';
            buttons[i].style.color = '';
        }
        
        // Reset textareas height
        var textareas = document.querySelectorAll('textarea');
        for (var i = 0; i < textareas.length; i++) {
            textareas[i].style.height = 'auto';
        }
        
        selectedCigaretteCount = null;
        isFormSubmitting = false;
        
        showToast('Form đã được reset', 'info');
    }
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

// Handle page visibility change
if (document.addEventListener) {
    document.addEventListener('visibilitychange', function() {
        if (document.hidden) {
            // Save form data to localStorage when page becomes hidden
            saveFormData();
        } else {
            // Restore form data when page becomes visible
            restoreFormData();
        }
    });
}

function saveFormData() {
    try {
        var formData = {
            cigarettes: document.getElementById('cigarettes').value,
            q1: document.getElementById('q1').value,
            q2: document.getElementById('q2').value,
            q3: document.getElementById('q3').value,
            q4: document.getElementById('q4').value,
            q5: document.getElementById('q5').value,
            timestamp: new Date().getTime()
        };
        localStorage.setItem('progressFormData', JSON.stringify(formData));
    } catch (e) {
        // localStorage not available
    }
}

function restoreFormData() {
    try {
        var savedData = localStorage.getItem('progressFormData');
        if (savedData) {
            var formData = JSON.parse(savedData);
            var now = new Date().getTime();
            
            // Only restore if data is less than 1 hour old
            if (now - formData.timestamp < 3600000) {
                document.getElementById('cigarettes').value = formData.cigarettes || '';
                document.getElementById('q1').value = formData.q1 || '';
                document.getElementById('q2').value = formData.q2 || '';
                document.getElementById('q3').value = formData.q3 || '';
                document.getElementById('q4').value = formData.q4 || '';
                document.getElementById('q5').value = formData.q5 || '';
                
                // Auto-resize textareas
                var textareas = document.querySelectorAll('textarea');
                for (var i = 0; i < textareas.length; i++) {
                    autoResizeTextarea(textareas[i]);
                }
                
                showToast('Dữ liệu form đã được khôi phục', 'info');
            } else {
                localStorage.removeItem('progressFormData');
            }
        }
    } catch (e) {
        // localStorage not available or JSON parse error
    }
}

// Clear saved data when form is successfully submitted
function clearSavedData() {
    try {
        localStorage.removeItem('progressFormData');
    } catch (e) {
        // localStorage not available
    }
}
