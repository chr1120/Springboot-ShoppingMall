<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{layouts/mainLayout}">

<th:block layout:fragment="script">
    <script th:inline="javascript">
        $(document).ready(function(){
            var errorMessage = [[${errorMessage}]];
            if(errorMessage != null){
                alert(errorMessage);
            }
            bindDomEvent();
        });

        function bindDomEvent(){
            $(".custom-file-input").on("change", function() {
                var fileName = $(this).val().split("\\").pop();  //이미지 파일명
                var fileExt = fileName.substring(fileName.lastIndexOf(".")+1); // 확장자 추출
                fileExt = fileExt.toLowerCase(); //소문자 변환

                if(fileExt != "jpg" && fileExt != "jpeg" && fileExt != "gif"
                 && fileExt != "png" && fileExt != "bmp"){
                    alert("이미지 파일만 등록이 가능합니다.");
                    return;
                }

                $(this).siblings(".custom-file-label").html(fileName);
            });
        }
    </script>
</th:block>

<th:block layout:fragment="css">
    <style>
        .input-group {
            margin-bottom : 15px
        }
        .img-div {
            margin-bottom : 10px
        }
        .fieldError {
            color: #bd2130;
        }
    </style>
</th:block>

<div layout:fragment="content">
    <form role="form" method="post" enctype="multipart/form-data" th:object="${itemFormDto}">

        <p class="h2">
            상품 등록
        </p>

        <input type="hidden" th:field="*{id}">

        <div class="form-group">
            <select th:field="*{itemSellStatus}" class="custom-select">
                <option value="SELL">판매중</option>
                <option value="SOLD_OUT">품절</option>
            </select>
        </div>

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">상품명</span>
            </div>
            <input type="text" th:field="*{itemName}" class="form-control" placeholder="상품명을 입력해주세요">
        </div>
        <p th:if="${#fields.hasErrors('itemName')}" th:errors="*{itemName}" class="fieldError">Incorrect data</p>

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">가격</span>
            </div>
            <input type="number" th:field="*{price}" class="form-control" placeholder="상품의 가격을 입력해주세요">
        </div>
        <p th:if="${#fields.hasErrors('price')}" th:errors="*{price}" class="fieldError">Incorrect data</p>

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">재고</span>
            </div>
            <input type="number" th:field="*{stock}" class="form-control" placeholder="상품의 재고를 입력해주세요">
        </div>
        <p th:if="${#fields.hasErrors('stock')}" th:errors="*{stock}" class="fieldError">Incorrect data</p>

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">상품 상세 내용</span>
            </div>
            <textarea class="form-control" aria-label="With textarea" th:field="*{itemDetail}"></textarea>
        </div>
        <p th:if="${#fields.hasErrors('itemDetail')}" th:errors="*{itemDetail}" class="fieldError">Incorrect data</p>

        <div th:if="${#lists.isEmpty(itemFormDto.itemImgDtoList)}">
            <div class="form-group" th:each="num: ${#numbers.sequence(1,5)}">
                <div class="custom-file img-div">
                    <input type="file" class="custom-file-input" name="itemImgFile">
                    <label class="custom-file-label" th:text="상품이미지 + ${num}"></label>
                </div>
            </div>
        </div>

        <div th:if = "${not #lists.isEmpty(itemFormDto.itemImgDtoList)}">
            <div class="form-group" th:each="itemImgDto, status: ${itemFormDto.itemImgDtoList}">
                <div class="custom-file img-div">
                    <input type="file" class="custom-file-input" name="itemImgFile">
                    <input type="hidden" name="itemImgIds" th:value="${itemImgDto.id}">
                    <label class="custom-file-label" th:text="${not #strings.isEmpty(itemImgDto.oriImgName)}
                     ? ${itemImgDto.oriImgName} : '상품이미지' + ${status.index+1}"></label>
                </div>
            </div>
        </div>

        <div th:if="${#strings.isEmpty(itemFormDto.id)}" style="text-align: center">
            <button th:formaction="@{/admin/item/new}" type="submit" class="btn btn-primary">저장</button>
        </div>
        <div th:unless="${#strings.isEmpty(itemFormDto.id)}" style="text-align: center">
            <button th:formaction="@{'/admin/item/' + ${itemFormDto.id} }" type="submit" class="btn btn-primary">수정</button>
        </div>
        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}">

    </form>

</div>

</html>