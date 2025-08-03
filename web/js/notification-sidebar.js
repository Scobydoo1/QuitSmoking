// Notification Sidebar JavaScript - NetBeans 8.3 & JDK 1.8 Compatible

// Global variables
// Notification Sidebar JavaScript - Cập nhật với click outside

var notificationSidebar = null;
var notificationOverlay = null;
var isSidebarOpen = false;

// Initialize when document ready
if (document.addEventListener) {
    document.addEventListener('DOMContentLoaded', function() {
        initializeNotificationSidebar();
    });
} else if (document.attachEvent) {
    document.attachEvent('onreadystatechange', function() {
        if (document.readyState === 'complete') {
            initializeNotificationSidebar();
        }
    });
}

function initializeNotificationSidebar() {
    notificationSidebar = document.getElementById('notificationSidebar');
    notificationOverlay = document.getElementById('notificationOverlay');
    
    // Click outside to close sidebar
    if (document.addEventListener) {
        document.addEventListener('click', function(event) {
            handleClickOutside(event);
        });
    } else if (document.attachEvent) {
        document.attachEvent('onclick', function(event) {
            handleClickOutside(event);
        });
    }
    
    // ESC key to close sidebar
    if (document.addEventListener) {
        document.addEventListener('keydown', function(event) {
            if (event.keyCode === 27 && isSidebarOpen) {
                closeNotificationSidebar();
            }
        });
    }
}

function handleClickOutside(event) {
    if (!isSidebarOpen) return;
    
    var target = event.target || event.srcElement;
    var clickedInsideSidebar = false;
    var clickedOnButton = false;
    
    // Kiểm tra xem có click vào sidebar không
    var currentElement = target;
    while (currentElement) {
        if (currentElement === notificationSidebar) {
            clickedInsideSidebar = true;
            break;
        }
        if (currentElement.className && currentElement.className.indexOf('notification-btn') > -1) {
            clickedOnButton = true;
            break;
        }
        currentElement = currentElement.parentNode;
    }
    
    // Đóng sidebar nếu click outside
    if (!clickedInsideSidebar && !clickedOnButton) {
        closeNotificationSidebar();
    }
}

function openNotificationSidebar() {
    if (notificationSidebar) {
        notificationSidebar.className = 'notification-sidebar show';
        isSidebarOpen = true;
        
        // Show overlay on mobile
        if (notificationOverlay && window.innerWidth <= 768) {
            notificationOverlay.className = 'notification-overlay show';
        }
        
        // Auto mark as read after 3 seconds
        setTimeout(function() {
            if (isSidebarOpen) {
                autoMarkAsRead();
            }
        }, 3000);
    }
}

function closeNotificationSidebar() {
    if (notificationSidebar) {
        notificationSidebar.className = 'notification-sidebar';
        isSidebarOpen = false;
        
        // Hide overlay
        if (notificationOverlay) {
            notificationOverlay.className = 'notification-overlay';
        }
    }
}

// Các function khác giữ nguyên như markAllAsRead, deleteNotification, etc.
function markAllAsRead() {
    var xhr;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject('Microsoft.XMLHTTP');
    }
    
    if (xhr) {
        xhr.open('POST', 'NotificationServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                try {
                    var response = JSON.parse(xhr.responseText);
                    if (response.success) {
                        updateUIAfterMarkAsRead();
                        showToast('Đã đánh dấu tất cả thông báo là đã đọc', 'success');
                    } else {
                        showToast(response.message || 'Có lỗi xảy ra', 'error');
                    }
                } catch (e) {
                    showToast('Có lỗi xảy ra', 'error');
                }
            }
        };
        
        xhr.send('action=markAllAsRead');
    }
}

// Kiểm tra trong Console Browser (F12)
function deleteNotification(notificationId) {
    console.log('Deleting notification:', notificationId); // Thêm dòng này để debug
    
    if (confirm('Bạn có chắc chắn muốn xóa thông báo này?')) {
        var xhr;
        if (window.XMLHttpRequest) {
            xhr = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            xhr = new ActiveXObject('Microsoft.XMLHTTP');
        }
        
        if (xhr) {
            xhr.open('POST', 'NotificationServlet', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4) {
                    console.log('Response status:', xhr.status); // Debug response
                    console.log('Response text:', xhr.responseText); // Debug response
                    
                    if (xhr.status === 200) {
                        try {
                            var response = JSON.parse(xhr.responseText);
                            if (response.success) {
                                removeNotificationFromUI(notificationId);
                                updateNotificationCount();
                                showToast('Đã xóa thông báo', 'success');
                            } else {
                                showToast(response.message || 'Không thể xóa thông báo', 'error');
                            }
                        } catch (e) {
                            console.error('JSON parse error:', e);
                            showToast('Có lỗi xảy ra', 'error');
                        }
                    } else {
                        console.error('HTTP Error:', xhr.status);
                        showToast('Lỗi kết nối server', 'error');
                    }
                }
            };
            
            xhr.send('action=deleteNotification&notificationId=' + encodeURIComponent(notificationId));
        }
    }
}

function autoMarkAsRead() {
    var unreadItems = document.querySelectorAll('.notification-item.unread');
    if (unreadItems.length > 0) {
        markAllAsRead();
    }
}

function updateUIAfterMarkAsRead() {
    var unreadItems = document.querySelectorAll('.notification-item.unread');
    for (var i = 0; i < unreadItems.length; i++) {
        var item = unreadItems[i];
        item.className = item.className.replace('unread', 'read');
        
        var unreadDot = item.querySelector('.unread-dot');
        if (unreadDot && unreadDot.parentNode) {
            unreadDot.parentNode.removeChild(unreadDot);
        }
    }
    
    var countElement = document.querySelector('.notification-count');
    if (countElement && countElement.parentNode) {
        countElement.parentNode.removeChild(countElement);
    }
    
    var unreadCountElement = document.querySelector('.unread-count');
    if (unreadCountElement) {
        unreadCountElement.textContent = 'Chưa đọc: 0';
    }
}

function removeNotificationFromUI(notificationId) {
    var notificationItem = document.querySelector('[data-notification-id="' + notificationId + '"]');
    if (notificationItem && notificationItem.parentNode) {
        notificationItem.style.transition = 'all 0.3s ease';
        notificationItem.style.transform = 'translateX(100%)';
        notificationItem.style.opacity = '0';
        
        setTimeout(function() {
            if (notificationItem.parentNode) {
                notificationItem.parentNode.removeChild(notificationItem);
                
                var remainingItems = document.querySelectorAll('.notification-item');
                if (remainingItems.length === 0) {
                    var notificationList = document.getElementById('notificationList');
                    if (notificationList) {
                        notificationList.innerHTML = 
                            '<div class="no-notifications">' +
                                '<i class="fas fa-bell-slash"></i>' +
                                '<h4>Không có thông báo nào</h4>' +
                                '<p>Bạn chưa có thông báo nào.</p>' +
                            '</div>';
                    }
                }
            }
        }, 300);
    }
}

function updateNotificationCount() {
    var remainingItems = document.querySelectorAll('.notification-item');
    var unreadItems = document.querySelectorAll('.notification-item.unread');
    
    var totalCountElement = document.querySelector('.total-count');
    if (totalCountElement) {
        totalCountElement.textContent = 'Tổng: ' + remainingItems.length;
    }
    
    var unreadCountElement = document.querySelector('.unread-count');
    if (unreadCountElement) {
        unreadCountElement.textContent = 'Chưa đọc: ' + unreadItems.length;
    }
    
    var buttonCount = document.querySelector('.notification-count');
    if (unreadItems.length === 0) {
        if (buttonCount && buttonCount.parentNode) {
            buttonCount.parentNode.removeChild(buttonCount);
        }
    } else if (buttonCount) {
        buttonCount.textContent = unreadItems.length;
    }
}

function showToast(message, type) {
    var toast = document.createElement('div');
    toast.className = 'toast toast-' + type;
    
    var icon = type === 'success' ? 'check-circle' : 'exclamation-circle';
    var color = type === 'success' ? '#27ae60' : '#e74c3c';
    
    toast.innerHTML = 
        '<div style="display: flex; align-items: center; gap: 8px;">' +
            '<i class="fas fa-' + icon + '"></i>' +
            '<span>' + message + '</span>' +
        '</div>';
    
    toast.style.position = 'fixed';
    toast.style.top = '20px';
    toast.style.left = '50%';
    toast.style.transform = 'translateX(-50%)';
    toast.style.background = color;
    toast.style.color = 'white';
    toast.style.padding = '12px 20px';
    toast.style.borderRadius = '8px';
    toast.style.zIndex = '9999';
    toast.style.boxShadow = '0 4px 12px rgba(0,0,0,0.15)';
    
    document.body.appendChild(toast);
    
    setTimeout(function() {
        if (toast.parentNode) {
            toast.parentNode.removeChild(toast);
        }
    }, 3000);
}
