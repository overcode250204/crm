$(document).ready(function () {
    $('.btn-xoa').click(function () {
        var id = $(this).attr('id-user');
        var This = $(this);
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/crm06/api/user?id=${id}`, // sử dụng String Template đúng cách
            //data: {name: "John", location: "Boston"}
        })
        .done(function (result) {
            if (result.data) {
                // This.parent().parent().remove(); // c1
                This.closest('tr').remove(); // tìm tới thẻ bao bọc của nó và xóa hết 1 thanh đó closest gần nhất thẻ gần nhất
            } else {
                alert("Xóa thất bại");
            }
        })
        .fail(function () {
            alert("Có lỗi xảy ra, vui lòng thử lại.");
        });
    });
});
