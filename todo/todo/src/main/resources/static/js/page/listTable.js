document.addEventListener('DOMContentLoaded',function(){

});
window.onload = function(){
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


}

//삭제이벤트
document.querySelector('.delBtn').addEventListener('click', () => {
    let form = new FormData(this.document.querySelector('.formList'));
    let obj = JSON.stringify(serialize(form));
    console.log(form);
    console.log(obj);
    // deleteTable(obj);
    location.reload();
})

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
                        '<button type="button" class="btn btn-info ms-2 delBtn">Delete</button>' +
                        '</form>' +
                        '</ul>'
                }
                inSpan.innerHTML = tag;
                // document.getElementById('contentList').value = xhr.response.content.value;

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

    xhr.open('DELETE', '/toDo/')
}

function serialize (rawData) {

    let rtnData = {};
    for (let [key, value] of rawData) {
        let sel = document.querySelectorAll("[name=" + key + "]");

        // Array Values
        if (sel.length > 1) {
            if (rtnData[key] === undefined) {
                rtnData[key] = [];
            }
            rtnData[key].push(value);
        }
        // Other
        else {
            rtnData[key] = value;
        }
    }
    return rtnData;
}
