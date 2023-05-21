package com.cybersoft.cozastore.controller;
import com.cybersoft.cozastore.payload.request.SignUpRequest;
import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * tên file bên BE ( back end ) sẽ đặt theo tên màn hình bên FE ( front end )
 * Cấu trúc response chuẫn bên ngoài hay sử dụng
 * statusCode: mã lỗi của chương trình
 * message: những câu thông báo tương ứng vs mã lỗi
 * data: dữ liệu trả ra nếu có ( đây là những dữ liệu trả ra cho FE xử lý
 * pakage payload : chứa tất cả những file quy định request và response của toàn bộ project
 * */
@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/signin")
    public ResponseEntity<?> signin(
            @RequestParam String username,
            @RequestParam String password

    ){
        BaseResponse baseResponse = new BaseResponse();
    boolean iSuscess = loginService.checklogin(username,password);

    baseResponse.setMessage(iSuscess ? "Đăng nhập thành công" : "Đăng nhập thất bại");
    baseResponse.setData(iSuscess);
        return new ResponseEntity (baseResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup (
            @RequestBody SignUpRequest usersignup

            )
    {
        boolean isSuccess = loginService.signup(usersignup);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(isSuccess? "Tạo tài khoản thành công" : "Tạo tài khoản không thành công");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

}
