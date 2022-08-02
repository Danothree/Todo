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
    // chkList : (chk, id) => {
    //     let obj;
    //     if (chk === 'true') {
    //         obj = {
    //             'id' : id,
    //             'completeCheck' : 'false'
    //         }
    //     } else {
    //         obj = {
    //             'id' : id,
    //             'completeCheck' : 'true'
    //         }
    //     }
    //     modifyTable(id, obj);
    //     location.reload();
    // }
}
//삭제
function todoDel(id) {
    if (confirm('삭제하시겠습니까?')) {
        deleteTable(id);
        location.reload();
    }
}
//수정
function todoModi(id) {
    let target = '.contentList'+id;
    const content = document.querySelector(target);
    const modify = prompt('할일 수정',content.innerHTML);
    content.innerHTML = modify;
    obj = {
        'id' : id,
        'content' : modify
    }
    modifyTable(id,obj);
    location.reload();
}

function chkList(chk, id) {
    let obj;
    let parentNode = document.querySelector('.li'+id);
    if (chk !== true) {
    obj = {
        'id' : id,
        'completeCheck' : 'true'
        }
        parentNode.classList.add('addSty');
    } else {
        obj = {
            'id' : id,
            'completeCheck' : 'false'
        }
        parentNode.classList.remove('addSty');
    }
    modifyTable(id, obj);
    location.reload();
}

function activeList() {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/todo/active', true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send('false');

    xhr.onload = function () {
        if(xhr.readyState === 4) {
            console.log('통신 완료');
        }
        if(xhr.status === 200) {
            let tag = '';
            let response = JSON.parse(xhr.response);
            const inSpan = document.querySelector('.inSpan');
            for (let i = 0; i < response.length; i++) {
                let chk = '';
                if (response[i].completeCheck === 'true') {
                    chk = 'checked';
                };
                tag += '<ul class="list-group mb-0">' +
                    '<form class="d-flex justify-content-center align-items-center mb-4 formList">' +
                    '<li class="list-group-item d-flex align-items-center border-0 rounded li'+response[i].id+'"' +
                    'style="background-color: #f4f6f7;">' +
                    '<input type="hidden" class="id" name="id" value="'+response[i].id+'"/>' +
                    '<input type="hidden" class="userId" name="userId" value="'+response[i].userId+'"/>' +
                    '<input class="form-check-input me-2" class="contentListChk" type="checkbox" '+chk+' onclick="chkList('+response[i].completeCheck+','+response[i].id+');" aria-label="..."/>' +
                    '<span class="contentList'+response[i].id+' contentList" name="contentList" style="color: #1b1e21">'+response[i].content+'</span>' +
                    '</li>' +
                    '<button type="button" class="btn btn-info ms-2 delBtn" onclick="todoModi('+response[i].id+');">수정</button>' +
                    '<button type="button" class="btn btn-info ms-2 delBtn" onclick="todoDel('+response[i].id+');">삭제</button>' +
                    '</form>' +
                    '</ul>'
            }
            inSpan.innerHTML = tag;
        }
    }
}

function completeList() {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/todo/active', true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send('true');

    xhr.onload = function () {
        if(xhr.readyState === 4) {
            console.log('통신 완료');
        }
        if(xhr.status === 200) {
            let tag = '';
            let response = JSON.parse(xhr.response);
            const inSpan = document.querySelector('.inSpan');
            for (let i = 0; i < response.length; i++) {
                let chk = '';
                if (response[i].completeCheck === 'true') {
                    chk = 'checked';
                };
                tag += '<ul class="list-group mb-0">' +
                    '<form class="d-flex justify-content-center align-items-center mb-4 formList">' +
                    '<li class="list-group-item d-flex align-items-center border-0 rounded li'+response[i].id+'"' +
                    'style="background-color: #f4f6f7;">' +
                    '<input type="hidden" class="id" name="id" value="'+response[i].id+'"/>' +
                    '<input type="hidden" class="userId" name="userId" value="'+response[i].userId+'"/>' +
                    '<input class="form-check-input me-2" class="contentListChk" type="checkbox" '+chk+' onclick="chkList('+response[i].completeCheck+','+response[i].id+');" aria-label="..."/>' +
                    '<span class="contentList'+response[i].id+' contentList" name="contentList" style="color: #1b1e21">'+response[i].content+'</span>' +
                    '</li>' +
                    '<button type="button" class="btn btn-info ms-2 delBtn" onclick="todoModi('+response[i].id+');">수정</button>' +
                    '<button type="button" class="btn btn-info ms-2 delBtn" onclick="todoDel('+response[i].id+');">삭제</button>' +
                    '</form>' +
                    '</ul>'
            }
            inSpan.innerHTML = tag;
        }
    }
}

function getList() {
    const xhr = new XMLHttpRequest;

    xhr.open('GET', '/todo', true);
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
                    let chk = '';
                    if (response[i].completeCheck === 'true') {
                        chk = 'checked';

                    };
                    tag += '<ul class="list-group mb-0">' +
                        '<form class="d-flex justify-content-center align-items-center mb-4 formList">' +
                        '<li class="list-group-item d-flex align-items-center border-0 rounded li'+response[i].id+'"' +
                        'style="background-color: #f4f6f7;">' +
                        '<input type="hidden" class="id" name="id" value="'+response[i].id+'"/>' +
                        '<input type="hidden" class="userId" name="userId" value="'+response[i].userId+'"/>' +
                        '<input class="form-check-input me-2" class="contentListChk" type="checkbox" '+chk+' onclick="chkList('+response[i].completeCheck+','+response[i].id+');" aria-label="..."/>' +
                        '<span class="contentList'+response[i].id+' contentList" name="contentList" style="color: #1b1e21">'+response[i].content+'</span>' +
                        '</li>' +
                        '<button type="button" class="btn btn-info ms-2 delBtn" onclick="todoModi('+response[i].id+');">수정</button>' +
                        '<button type="button" class="btn btn-info ms-2 delBtn" onclick="todoDel('+response[i].id+');">삭제</button>' +
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

    xhr.open('POST', '/todo', true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(obj));

    xhr.onload = function () {
        if(xhr.status === 200) {
            console.log(xhr.response);
        } else {
            let parse = JSON.parse(xhr.response);

            alert(parse.innerHTML);
        }
    }
}

function deleteTable(id) {
    const xhr = new XMLHttpRequest();

    xhr.open('DELETE', '/todo/'+id, true);
    xhr.send();

    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log('삭제 완료');
        }
    }
}

function modifyTable(id,obj) {
    const xhr = new XMLHttpRequest();

    xhr.open('PATCH', '/todo/'+id, true);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(obj));

    xhr.onload = function () {
        if(xhr.status === 200) {
            console.log('수정 완료');

        } else {
            console.log(xhr.response);
        }
    }
}
