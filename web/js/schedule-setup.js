/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Schedule Setup JavaScript - NetBeans 8.3 & JDK 1.8 Compatible

// Initialize when document ready
if (document.addEventListener) {
    document.addEventListener('DOMContentLoaded', function() {
        initializeScheduleSetup();
    });
} else if (document.attachEvent) {
    document.attachEvent('onreadystatechange', function() {
        if (document.readyState === 'complete') {
            initializeScheduleSetup();
        }
    });
}

function initializeScheduleSetup() {
    // Initialize date validation
    initializeDateValidation();
    
    // Initialize time preview
    initializeTimePreview();
    
    // Initialize form validation
    initializeFormValidation();
}

function initializeDateValidation() {
    var startDateInput = document.getElementById('startDate');
    if (startDateInput) {
        // Set minimum date to today
        var today = new Date();
        var todayString = today.toISOString().split('T')[0];
        startDateInput.min = todayString;
        
        if (startDateInput.addEventListener) {
            startDateInput.addEventListener('change', function() {
                validateStartDate(this);
            });
        }
    }
}

function validateStartDate(input) {
    var selectedDate = new Date(input.value);
    var dayOfWeek = selectedDate.getDay(); // 0 = Sunday, 1 = Monday, etc.
    
    if (dayOfWeek !== 1) { // Not Monday
        alert('Ngày bắt đầu phải là thứ Hai!');
        input.value = '';
        
        // Find next Monday
        var nextMonday = new Date(selectedDate);
        var daysUntilMonday = (8 - dayOfWeek) % 7;
        if (daysUntilMonday === 0) daysUntilMonday = 7;
        nextMonday.setDate(selectedDate.getDate() + daysUntilMonday);
        
        var nextMondayString = nextMonday.toISOString().split('T')[0];
        alert('Thứ Hai gần nhất là: ' + formatDate(nextMonday));
    }
}

function initializeTimePreview() {
    var timeSelect = document.getElementById('startTime');
    var timePreview = document.getElementById('timePreview');
    
    if (timeSelect && timePreview) {
        if (timeSelect.addEventListener) {
            timeSelect.addEventListener('change', function() {
                updateTimePreview(this.value, timePreview);
            });
        }
    }
}

function updateTimePreview(selectedTime, previewElement) {
    if (!selectedTime) {
        previewElement.innerHTML = '<i class="fas fa-clock"></i><span>Chọn ca học để xem thời gian chi tiết</span>';
        return;
    }
    
    var timeMap = {
        '07:00:00': 'Ca sáng sớm: 7:00 - 9:00 (2 giờ)',
        '09:15:00': 'Ca sáng: 9:15 - 11:15 (2 giờ)',
        '13:00:00': 'Ca chiều: 13:00 - 15:00 (2 giờ)',
        '15:15:00': 'Ca chiều muộn: 15:15 - 17:15 (2 giờ)'
    };
    
    var timeText = timeMap[selectedTime] || 'Thời gian đã chọn';
    previewElement.innerHTML = '<i class="fas fa-check-circle"></i><span>' + timeText + '</span>';
    previewElement.style.background = '#e8f5e8';
    previewElement.style.color = '#1b5e20';
}

function initializeFormValidation() {
    var form = document.getElementById('scheduleForm');
    if (form) {
        if (form.addEventListener) {
            form.addEventListener('submit', function(event) {
                return validateForm(event);
            });
        }
    }
}

function validateForm(event) {
    var isValid = true;
    var errorMessages = [];
    
    // Validate start date
    var startDate = document.getElementById('startDate').value;
    if (!startDate) {
        errorMessages.push('Vui lòng chọn ngày bắt đầu');
        isValid = false;
    } else {
        var selectedDate = new Date(startDate);
        var dayOfWeek = selectedDate.getDay();
        if (dayOfWeek !== 1) {
            errorMessages.push('Ngày bắt đầu phải là thứ Hai');
            isValid = false;
        }
    }
    
    // Validate days selection
    var daysRadios = document.getElementsByName('days');
    var daysSelected = false;
    for (var i = 0; i < daysRadios.length; i++) {
        if (daysRadios[i].checked) {
            daysSelected = true;
            break;
        }
    }
    if (!daysSelected) {
        errorMessages.push('Vui lòng chọn lịch học trong tuần');
        isValid = false;
    }
    
    // Validate time selection
    var startTime = document.getElementById('startTime').value;
    if (!startTime) {
        errorMessages.push('Vui lòng chọn ca học');
        isValid = false;
    }
    
    if (!isValid) {
        event.preventDefault();
        alert(errorMessages.join('\n'));
        return false;
    }
    
    // Show loading state
    var submitBtn = event.target.querySelector('button[type="submit"]');
    if (submitBtn) {
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Đang tạo lịch...';
        submitBtn.disabled = true;
    }
    
    return true;
}

function formatDate(date) {
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    
    return (day < 10 ? '0' : '') + day + '/' + 
           (month < 10 ? '0' : '') + month + '/' + year;
}
