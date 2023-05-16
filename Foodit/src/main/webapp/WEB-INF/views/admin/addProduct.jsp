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
  function itemsinsertOk(){
	 	const f = document.addProductForm;
  
	   	f.action = "${pageContext.request.contextPath}/admin/${mode}_ok.do";
	    f.submit();
	}
  
        
  </script>
  
</head>
<body>

  <div id="container">



          <div id="viewOrderList" class="page_section section_orderlist">
            <div class="head_aticle">
              <h2 class="tit">
                ${title}
              </h2>
              
            </div>

            <div class="list_order">
              <form name="addProductForm" method="post" class="">
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
                                            <input type="number" name="price" id="price" class="i_text text3">
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
                                                
                                                    <option value="1">야채/과일</option>
                                                    <option value="2">해/수산물</option>
                                                    <option value="3">정육</option>
                                              </select>
                                        </span>
                                    </div>
                                </td>
                            </tr>
                            
                           <tr>
                                <th >브랜드번호</th>
                                <td colspan="2"> 
                                    <div>
                                        <span>
                                            <input type="number" name="brandNo" id="brandNo" class="i_text text1" onkeyup="chkword_name(this, 80)">
                                        </span>
                                    </div>

                                </td>
                            </tr>
                     		  <tr>
                                <th >첨부</th>
                                <td colspan="2"> 
                                    <div>
                                        <span>
                                            <input type="file" name="brandNo" id="brandNo" class="form-control">
                                        </span>
                                    </div>

                                </td>
                            </tr>
              
                     
                        </tbody>
                    </table>
                  </div>
					<div id="product_submit" class="pd_submit">
                    <button type="button" onclick="itemsinsertOk();">등록하기</button>
                  </div>

                </div>
            </form>


              
            </div>
          </div>

        </div>
</body>
</html>