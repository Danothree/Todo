window.onload = function(){
    main.init();
}

const main = {
    init : () => {
        getList();
        // 클릭 이벤트
        document.getElementById('addBtn').addEventListener('click',() => {
            const userId = document.getElementById('userId').value;
            const content = document.getElementById('content').value;

            //input null check
            if(!userId) {
                alert('아이디를 입력해주세요');
                return false;
            }else if(!content) {
                alert('내용을 입력해주세요');
                return false;
            }
            console.log(userId);
            console.log(content);
            let obj = {
                "userId" : userId,
                "content" : content
            }
            saveList(obj);
            location.reload();
        });
    },
}
//삭제
function todoDel(userId,content) {
    let obj = {
        "userId" : userId,
        "content": content
    }
    deleteTable(obj);
    location.reload();
}

function getList() {
    const xhr = new XMLHttpRequest;

    xhr.open('GET', '/toDo', true);
    xhr.send();

    xhr.onload = function () {
        if(xhr.readyState === 4) {
            console.log('통신 완료');
            if(xhr.status === 200) {
                let tag = '';
                console.log(xhr.response);
                let response = JSON.parse(xhr.response);
                const inSpan = document.querySelector('.inSpan');
                console.log(response[0].content);
                console.log(response.length);
                for (let i = 0; i < response.length; i++) {
                    tag += '<ul class="list-group mb-0">' +
                        '<form class="d-flex justify-content-center align-items-center mb-4 formList">' +
                        '<li class="list-group-item d-flex align-items-center border-0 rounded"' +
                        'style="background-color: #f4f6f7;">' +
                        '<input type="hidden" class="userId" name="userId" value="'+response[i].userId+'"/>' +
                        '<input class="form-check-input me-2" class="contentListChk" type="checkbox" value="'+response[i].completeCheck+'" aria-label="..."/>' +
                        '<span class="contentList" name="contentList" style="color: #1b1e21" value="'+response[i].content+'">'+response[i].content+'</span>' +
                        '</li>' +
                        '<button type="button" class="btn btn-info ms-2 delBtn" onclick="todoDel('+response[i].userId+','+response[i].content+');">Delete</button>' +
                        '</form>' +
                        '</ul>'
                }
                inSpan.innerHTML = tag;
            }
        }
    };
}

function saveList(obj) {
    const xhr = new XMLHttpRequest;

    xhr.open('POST', '/toDo', true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(obj));

    xhr.onload = function () {
        if(xhr.status === 200) {
            console.log(xhr.response);
        }
    }
}

function deleteTable(obj) {
    const xhr = new XMLHttpRequest();

    xhr.open('DELETE', '/toDo', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(obj);

    xhr.onload = function () {
        if(xhr.status === 200) {
            console.log('삭제 완료');
        }
    }
}
