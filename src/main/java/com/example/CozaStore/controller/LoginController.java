package com.example.CozaStore.controller;

import com.example.CozaStore.dto.UserDto;
import com.example.CozaStore.payload.request.SignUpRequest;
import com.example.CozaStore.payload.response.BaseResponse;
import com.example.CozaStore.service.LoginService;
import com.example.CozaStore.utils.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Tên file bên BE sẽ đặt theo tên màn hình bên FE
 *
 *  Cấu trúc response chuẩn bên ngoài hay sử dụng
 *      statusCode: Mã lỗi của chương trình
 *      message: Những câu thông báo tương ứng với mã lỗi
 *      data: Dữ liệu trả ra nếu có (đây là dữ liệu trả ra cho FE xử lý)
 *
 *  Package payload : Chứa tất cả những file quy định request và respone của toàn bộ project
 *
 */
@RestController
@RequestMapping("/login")
// Cho phép domain khác gọi vào link API
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    JWTHelperUtils jwtHelperUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<?> signin (@RequestParam String username, @RequestParam String password){
        BaseResponse response = new BaseResponse();
        try {
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username,password);
            authenticationManager.authenticate(user);

            String token = jwtHelperUtils.generateToken(username);

            response.setMessage("Đăng nhập thành công");
            response.setData(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatusCode(403);
            response.setMessage("Đăng nhập thất bại");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> insertUser (@RequestBody SignUpRequest signup){
        boolean isSuccess = loginService.insertUser(signup);

        BaseResponse response = new BaseResponse();
        response.setMessage(isSuccess ? "Đăng ký thành công" : "Đăng ký thất bại");
        response.setData(isSuccess);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/token")
    public ResponseEntity<?> index(){
        String token = jwtHelperUtils.generateToken("vmh123");
        String data = jwtHelperUtils.validToken(token);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
