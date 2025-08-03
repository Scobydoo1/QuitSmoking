/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function ready(fn) {
    if (document.readyState !== 'loading') fn();
    else document.addEventListener('DOMContentLoaded', fn);
}
ready(function() {
    var container   = document.getElementById('questions');
    var hiddenCount = document.getElementById('questionCount');
    var spanDisplay = document.getElementById('countDisplay');
    var MAX_Q       = 10;

    function updateCount() {
        var n = container.getElementsByClassName('question-item').length;
        hiddenCount.value = n;
        spanDisplay.innerHTML = n;
        // Badge màu khi đủ 10 câu
        var badge = document.getElementById('countBadge');
        if (badge) {
            badge.style.background = (n >= MAX_Q) ? "#e74c3c" : "#27ae60";
        }
    }

    function renumber() {
        var items = container.getElementsByClassName('question-item');
        for (var i = 0; i < items.length; i++) {
            var num   = i + 1;
            var label = items[i].getElementsByTagName('label')[0];
            var input = items[i].getElementsByTagName('input')[0];
            var btn   = items[i].getElementsByTagName('button')[0];

            label.htmlFor     = 'qs' + num;
            label.innerHTML   = 'Câu hỏi ' + num + ':';
            input.id          = 'qs' + num;
            input.name        = 'qs' + num;
            input.placeholder = 'Nhập câu hỏi ' + num;
            btn.setAttribute('data-index', num);
        }
    }

    window.addQuestion = function() {
        if (container.getElementsByClassName('question-item').length >= MAX_Q) return;
        var div = document.createElement('div');
        div.className = 'question-item';
        div.innerHTML =
            '<label></label>' +
            '<input type="text" maxlength="255" required />' +
            '<button type="button" class="remove-btn" title="Xóa câu hỏi"><i class="fas fa-times"></i></button>';
        var btn = div.getElementsByTagName('button')[0];
        btn.onclick = function() {
            div.style.background = '#ffeaea';
            div.style.transition = 'background 0.2s';
            setTimeout(function() {
                container.removeChild(div);
                renumber();
                updateCount();
            }, 150);
        };
        container.appendChild(div);
        renumber();
        updateCount();
        div.style.opacity = 0;
        setTimeout(function() {
            div.style.transition = 'opacity 0.5s';
            div.style.opacity = 1;
        }, 10);
    };

    // Khởi tạo 1 câu hỏi mặc định
    window.addQuestion();

    // Đổi màu badge khi đủ 10 câu
    updateCount();
    var addBtn = document.getElementById('addBtn');
    if (addBtn) {
        addBtn.onclick = function() {
            window.addQuestion();
            updateCount();
        };
    }
});
