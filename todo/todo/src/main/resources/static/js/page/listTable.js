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


    });
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
                let inSpan = document.querySelector('.inSpan');
                console.log(response[0].content);
                console.log(response.length);
                for (let i = 0; i < response.length; i++) {
                    tag += '<input class="form-check-input me-2" class="contentListChk" type="checkbox" value="" aria-label="..." checked />\n' +
                    '<span class="contentList" name="contentList" style="color: #1b1e21" value="'+response[i].content+'"></span>';
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
    console.log(obj);
    xhr.send(JSON.stringify(obj));

    xhr.onload = function () {
        if(xhr.status === 200) {
            console.log(xhr.response);
        }
    }
}