<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>품목 추가</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/admin/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/admin/admin_page_prd_upload.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/admin/css/admin_common.css">
  <script>
  function itemsinsertOk() {
		const f = document.memberForm;
  
	   	f.action = "${pageContext.request.contextPath}/admin/addProduct_OK.do";
	    f.submit();
	}
  
        function chkword_name(obj, maxByte){                      // 상품명 글자수 제한 두기
            var strValue = obj.value;         //strValue : 글자수 값을 저장하기 위한 변수
            var strLen = strValue.length;     //strLen : strValue 값의 길이
            var totalByte = 0;                //글자수 총합
            var len = 0;
            var oneChar = "";
            var str2= "";

            for (var i = 0; i < strLen; i++){
                oneChar = strValue.charAt(i);             //oneChar : 문자 값 리턴 받는 함수
                if(escape(oneChar).length > 4) {          //한글이면 글자수 + 2
                    totalByte += 2;
                    $('.txt_count_name').text(totalByte);
                } else {                                   //그외(숫자,영어)는 +1
                    totalByte++;
                    $('.txt_count_name').text(totalByte);
                }

                if(totalByte <= maxByte){
                    len = i + 1;
                }

            }

            if (strLen == 0){
                $('.txt_count_name').text(0);
            }


            if(totalByte > maxByte){                      // 맥스값보다 글자수가 초과되면 알림후에 뒷글자 자르기
                alert(maxByte + "자를 초과 입력 할 수 없습니다.");      
                str2 = strValue.substr(0, len);               
                obj.value = str2;
                chkword_name(obj, 4000);
            }
        }







        function chkword_introduce(obj, maxByte){                //한줄소개 글자수 제한 두기
            var strValue = obj.value;
            var strLen = strValue.length;
            var totalByte = 0;
            var len = 0;
            var oneChar = "";
            var str2= "";

            for (var i = 0; i < strLen; i++){
                oneChar = strValue.charAt(i);
                if(escape(oneChar).length > 4) {
                    totalByte += 2;
                    $('.txt_count_introduce').text(totalByte);
                } else {
                    totalByte++;
                    $('.txt_count_introduce').text(totalByte);
                }

                if(totalByte <= maxByte){
                    len = i + 1;
                }

            }

            if (strLen == 0){
                $('.txt_count_introduce').text(0);
            }

            if(totalByte > maxByte){
                alert(maxByte + "자를 초과 입력 할 수 없습니다.");
                str2 = strValue.substr(0, len);
                obj.value = str2;
                chkword_introduce(obj, 4000);
            }
        }



        function chkword_information(obj, maxByte){            //안내사항 글자수 제한두기
            var strValue = obj.value;
            var strLen = strValue.length;
            var totalByte = 0;
            var len = 0;
            var oneChar = "";
            var str2= "";

            for (var i = 0; i < strLen; i++){
                oneChar = strValue.charAt(i);
                if(escape(oneChar).length > 4) {
                    totalByte += 2;
                    $('.txt_count_information').text(totalByte);
                } else {
                    totalByte++;
                    $('.txt_count_information').text(totalByte);
                }

                if(totalByte <= maxByte){
                    len = i + 1;
                }

            }

            if (strLen == 0){
                $('.txt_count_information').text(0);
            }

            if(totalByte > maxByte){
                alert(maxByte + "자를 초과 입력 할 수 없습니다.");
                str2 = strValue.substr(0, len);
                obj.value = str2;
                chkword_information(obj, 4000);
            }
        }



      
  </script>


  <script>
      function img_remove(obj){
        var obj_name = $(obj).attr('name');
        console.log(obj_name);
          
        a = $('#'+obj_name)
        $('#'+obj_name).empty();
       

      }
  </script>
  
</head>
<body>

  <div id="container">



          <div id="viewOrderList" class="page_section section_orderlist">
            <div class="head_aticle">
              <h2 class="tit">
                상품 등록
              </h2>
              
            </div>

            <div class="list_order">
              <form action="" class="">
                <div class="size">
                  <div class="p_board">
                    <table width="100%"> 
                        <tbody>
                            <tr>
                                <th >상품명</th>
                                <td colspan="2"> 
                                    <div>
                                        <span>
                                            <input type="text" name="itemName" id="itemName" class="i_text text1" onkeyup="chkword_name(this, 80)">
                                        </span>
                                    </div>

                                </td>
                            </tr>
                            
                		    <tr>
                                <th>가격</th>
                                <td colspan="2">
                                    <div>
                                        <span>
                                            <input type="text" name="price" id="price" class="i_text text3">
                                        </span>
                                    </div>
                                    <span class="won">
                                      원
                                    </span>
                                    <span class="won_type">
                                      숫자만 입력해주세요 !
                                    </span>
                                </td>
                            </tr>
                        	 <tr>
                                <th >할인금액</th>
                                <td colspan="2"> 
                                    <div>
                                        <span>
                                            <input type="text" name="discount" id="discount" class="i_text text1" >
                                        </span>
                                    </div>

                                </td>
                            </tr>
                             <tr>
                                <th>수량</th>
                                <td colspan="2">
                                    <div>
                                        <span>
                                            <input type="number" name="cnt" id="cnt" class="i_text num1" placeholder="0">
                                        </span>
                                    </div>
                                    
                                </td>
                            </tr>

                            <tr>
                                <th>판매단위</th>
                                <td colspan="2">
                                    <div>
                                        <span>
                                            <input type="text" name="saleUnit" id="saleUnit" class="i_text text4">
                                        </span>
                                    </div>
                                    <span class="input_ex">
                                      예) 1봉, 1통, 1개
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <th>상품설명</th>
                                <td>
                                    <div>
                                        <span>
                                            <textarea class="i_text text8" name="description" id="description" cols="30" rows="10" onkeyup="chkword_information(this, 550)"></textarea>
                                        </span>
                                    </div>

                                </td>
                            </tr>
        
                             <tr>
                                <th>유통기한</th>
                                <td>
                                    <div>
                                        <span>
                                            <input type="text" name="deadline" id="deadline" class="i_text text7">
                                        </span>
                                    </div>
                                </td>
                            </tr>
                          
                            <tr>
                                <th>카테고리</th>
                                <td>
                                    <div>
                                        <span>
                                            <select name="categoryNo" id="categoryNo" class="product_category">
                                                <option value="">------- 선택하세요 -------</option>
                                                
                                                    <option value="1">-야채/과일</option>
                                                    <option value="2">-해/수산물</option>
                                                    <option value="3">-정육</option>
                                              </select>
                                        </span>
                                    </div>
                                </td>
                            </tr>
                            
                           <tr>
                                <th >브랜드명</th>
                                <td colspan="2"> 
                                    <div>
                                        <span>
                                            <input type="text" name="brandName" id="brandName" class="i_text text1" onkeyup="chkword_name(this, 80)">
                                        </span>
                                    </div>

                                </td>
                            </tr>
                            
                       
                            
                        </tbody>
                    </table>
                  </div>
					<div id="product_submit" class="pd_submit">
                    <button type="submit" onclick="itemsinsertOk();">등록하기</button>
                  </div>

                </div>
            </form>


              
            </div>
          </div>

        </div>
</body>
</html>