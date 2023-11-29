$("#gogobtn").click(async function () {

            let name = $('#name').val();
            let content = $('#content').val();
            let pass = $('#pass').val();


            let doc = {
                'name': name,
                'pass': pass,
                'content': content

            };

            await addDoc(collection(db, "abc"), doc);

            alert('저장 완료!');
            window.location.reload();
        })
        //            <div class="card-body" data-pass="${pass}" data-entry-id="${doc.id}">
        //         <h5 class="card-title">${name}</h5>   
        //         <p class="card-text">${content}</p>
        //         <a href="#" id="del" class="btn btn-danger">삭제</a>
        //         <a href="#" id="ch" class="btn btn-light">수정</a>
        //     </div>`;
        let docs = await getDocs(collection(db, "abc"));
        docs.forEach((doc) => {
            let row = doc.data();

            let name = row['name'];
            let pass = row['pass'];
            let content = row['content'];

            let temp_html = `
            <div class="card-body" data-pass="${pass}" data-entry-id="${doc.id}">
             <h5 class="card-title">${name}</h5>   
                <p class="card-text">${content}</p>
                <a href="#" id="del" class="btn btn-danger">삭제</a>
                <a  href="#" 
                    id="ch" 
                    class="btn btn-light">수정</a>
                    
            </div>`;
            $('#naeyongdw').append(temp_html);
        });



        $('#naeyongdw').on('click', '.btn-danger', async function () {

            // 선택한 방명록에 해당되는 document ID 를 변수 지정 
            const entryId = $(this).closest('.card-body').data('entry-id');
            console.log(entryId);

            //그 아이디를 삭제하려고 할떄 비밀번호칸에 pass를 맞게 넣어야함.
            

            const delj = $(this).closest('.card-body').data('pass');

            //조건문 비밀번호 일치시 삭제하고 아니면 경고창만 띄워라            
            if (delj == prompt('비밀번호 입력')) {
                await deleteDoc(doc(db, "abc", entryId))
                alert('삭제완료');
            } else {
                alert('비밀번호 불일치')
            }

            window.location.reload();
        })