package jp.co.internous.easylogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.easylogin.model.domain.MstUser;
import jp.co.internous.easylogin.model.mapper.MstUserMapper;

@Controller
//このクラスがSpring(フレームワーク)コントローラであることを示す。
@RequestMapping("/easylogin")
//このクラスが処理するリクエストのベースURLを指定している。
public class LoginController {
	/*＠Autowiredアノテーションを付与して宣言されたオブジェクトは
	 * Spring　Bootによって自動的にインスタンス化される。
	 * 
	 * 言い換えると、Spring BootがLoginControllerクラスのオブジェクトを
	 * 自動的にインスタンス化する。
	 * そしてMstUserMapperも自動でインスタンス化し、LoginControllerオブジェクトに注入する。
	 * つまり、@Autowiredを使用することで、依存関係に注入する手作業が減り、
	 * コードの保守性や拡張性が向上する。
	 */
	@Autowired
	private MstUserMapper userMapper;
	
	@GetMapping("/")
	/*HTTP GETリクエストが"easylogin"に送信された場合に、
	 * index()メソッドが呼び出されることを指定している。
	 * そしてindex()メソッドは、"index"という名前のビューを
	 * 返すように定義されている。*/
	public String index() {
		return "index" ;
	}
	
	@GetMapping("login")
	public String login(String userName, String password, Model model) {
		//mapperを使ってデータベースにアクセスする（MstUserMapperを参照)
		MstUser user = userMapper.findByUserNameAndPassword(userName, password);
		if(user == null) {
			model.addAttribute("message" , "ゲストさん、ようこそ！");
		} else {
			model.addAttribute("message", user.getFullName() + "さん、ようこそ！");
		}
		//login.htmlに遷移させる
		return "login";
	}

}