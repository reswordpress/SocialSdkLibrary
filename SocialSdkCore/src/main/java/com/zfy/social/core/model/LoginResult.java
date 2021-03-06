package com.zfy.social.core.model;

import com.zfy.social.core.exception.SocialError;
import com.zfy.social.core.model.token.AccessToken;
import com.zfy.social.core.model.user.SocialUser;

/**
 * CreateAt : 2016/12/25
 * Describe : 登陆结果
 *
 * @author chendong
 */

public class LoginResult extends Result {

    // 返回的基本用户信息
    // 针对登录类型可强转为 WbUser,WxUser,QQUser 来获取更加丰富的信息
    public SocialUser socialUser;
    // 本次登陆的 token 信息，openId, unionId,token,expires_in
    public AccessToken accessToken;
    // 授权码，如果 onlyAuthCode 为 true, 将会返回它
    public String wxAuthCode;

    public LoginResult(int state, int target) {
        super(state, target);
    }

    public static LoginResult startOf(int target) {
        return new LoginResult(STATE_START, target);
    }

    public static LoginResult successOf(int target, SocialUser baseUser, AccessToken baseToken) {
        LoginResult result = new LoginResult(STATE_SUCCESS, target);
        result.socialUser = baseUser;
        result.accessToken = baseToken;
        return result;
    }

    public static LoginResult successOf(int target, String wxAuthCode) {
        LoginResult result = new LoginResult(STATE_SUCCESS, target);
        result.wxAuthCode = wxAuthCode;
        return result;
    }

    public static LoginResult failOf(int target, SocialError error) {
        LoginResult result = new LoginResult(STATE_FAIL, target);
        result.error = error;
        return result;
    }

    public static LoginResult cancelOf(int target) {
        return new LoginResult(STATE_CANCEL, target);
    }

    public static LoginResult completeOf(int target) {
        return new LoginResult(STATE_COMPLETE, target);
    }

    public static LoginResult stateOf(int state, int target) {
        return new LoginResult(state, target);
    }


    @Override
    public String toString() {
        return "LoginResult2{" +
                "target=" + target +
                ", socialUser=" + socialUser +
                ", accessToken=" + accessToken +
                ", wxAuthCode='" + wxAuthCode + '\'' +
                '}';
    }
}
