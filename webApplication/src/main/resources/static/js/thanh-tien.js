const thanhTien = document.getElementsByName('thanhTien');
var result = 0;
for(let i = 0 ; i < thanhTien.length ; i++){
    result  +=  Number.parseInt(thanhTien[i].innerText , Number);
}
const  tongTien = document.getElementById('tongTien');
tongTien.innerHTML += result;
