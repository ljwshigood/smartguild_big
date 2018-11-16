package com.zzteck.bigbwg.webmanager;

import android.content.Context;

import com.google.gson.Gson;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.utils.Constant;
import com.zzteck.bigbwg.utils.SharedPreferencesUtils;

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 
 * private static boolean uploadChatRecordSMS(Context context, ChatRecodeSMS recode,
			AsyncHttpResponseHandler responseHandler) {
		JSONObject json_bodies = new JSONObject();
		try {
			json_bodies.put("msg", recode.getMsg());
			json_bodies.put("type", recode.getType());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json_payload = new JSONObject();
		try {
			json_payload.put("bodies", json_bodies);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject json_recode = new JSONObject();

		AsyncHttpClient client = new HttpRequestClient().getClient();

		try {
			json_recode.put("type", "chatmessage");
			json_recode.put("from", recode.getFrom());
			json_recode.put("to", recode.getTo());
			json_recode.put("msg_id", "" + recode.getTimestamp());
			json_recode.put("timestamp", recode.getTimestamp());
			json_recode.put("chat_type", recode.getChat_type());
			json_recode.put("payload", json_payload);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringEntity entity = null;
		try {
			entity = new StringEntity(json_recode.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String user_id = EMClient.getInstance().getCurrentUser();
		String url = apiUrl + uploadChatRecodeUrl + user_id;

		client.post(context, url, entity, "application/json", responseHandler);
		return false;
	}
 * 
 * 
 * 
 * */

public class WebActManager {

    private static WebActManager mInstance;

    private WebManager mWebManager;

    private Context mContext;

    private WebActManager(Context context) {
        super ();
        mContext = context;
        mWebManager = WebManager.getInstance ( context );
    }

    public static WebActManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new WebActManager( context );

        }
        return mInstance;
    }

	public void relicWenChuangLists(Context context , int relic_type, String id, String name, final IActManager mIActManager) {


		JSONObject json = new JSONObject();
		try {
			json.put ( "relic_type", relic_type );
			json.put ( "id", id );
			json.put ( "name", name );
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

		Request request = new Request.Builder()
				.url(Constant.HOST+Constant.RELIC_LIST)
				.post(requestBody)
				.build();



		Call call = WebManager.getInstance(context).okHttpClient.newCall(request);

		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("连接失败");
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {

				String json = response.body().string() ;
				Gson gson = new Gson() ;
				NearWenChuangBean bean = gson.fromJson ( json, NearWenChuangBean.class );
				if(mIActManager != null){
					mIActManager.IRelicWenChuangLists(bean);
				}
			}

		});
	}


    public void relicLists(Context context , int relic_type, String id, String name, final IActManager mIActManager) {


		JSONObject json = new JSONObject();
		try {
			json.put ( "relic_type", relic_type );
			json.put ( "id", id );
			json.put ( "name", name );
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

		Request request = new Request.Builder()
				.url(Constant.HOST+Constant.RELIC_LIST)
				.post(requestBody)
				.build();



		Call call = WebManager.getInstance(context).okHttpClient.newCall(request);

		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("连接失败");
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {

				String json = response.body().string() ;
				Gson gson = new Gson() ;
				NearWenWuBean bean = gson.fromJson ( json, NearWenWuBean.class );
				if(mIActManager != null){
					mIActManager.IRelicLists(bean);
				}
			}

		});
    }

	public void getBwg1(Context context, String id, final IActManager iActManager) {

		JSONObject json = new JSONObject();
		try {
			json.put ( "id", id );
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

		Request request = new Request.Builder()
				.url(Constant.HOST + Constant.GET_BWG_DETAIL)
				.post(requestBody)
				.build();

		Call call = WebManager.getInstance(context).okHttpClient.newCall(request);

		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("连接失败");

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String string = response.body().string() ;
				Gson gson = new Gson() ;
				BwgBean bean = gson.fromJson(string, BwgBean.class) ;
				if(iActManager != null){
					iActManager.IBwgDetail(bean);
				}
			}

		});

	}

	public void getBwg(Context context, String id, final IActManager mIActManager) {

		JSONObject json = new JSONObject();
		try {
			json.put ( "id", id );
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

		Request request = new Request.Builder()
				.url(Constant.HOST + Constant.GET_BWG_DETAIL)
				.post(requestBody)
				.build();

		Call call = WebManager.getInstance(context).okHttpClient.newCall(request);

		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("连接失败");

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String string = response.body().string() ;
				Gson gson = new Gson() ;
				BwgBean bean = gson.fromJson(string, BwgBean.class) ;
				if(mIActManager != null){
					mIActManager.IBwgDetail(bean);
				}
			}

		});

	}

    public void loginWeb(final Context context, String code, final IActManager iActManager) {

		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), code);

		Request request = new Request.Builder()
				.url(Constant.HOST_LOGIN)
				.post(requestBody)
				.build();

		Call call = WebManager.getInstance(context).okHttpClient.newCall(request);

		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("连接失败");
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String string = response.body().string() ;
				Gson gson = new Gson() ;
				LoginBean bean = gson.fromJson(string,LoginBean.class) ;
				if(bean.getErrcode() == 200){
					SharedPreferencesUtils.setParam(context,"token",bean.getData().getToken());
					if(iActManager != null){
						iActManager.ILogin(bean);
					}
				}
			}

		});

    }

    public void activityDetail(Context context, String id, final IActManager mIActManager) {

        JSONObject json = new JSONObject();
        StringEntity entity = null;
        try {
            json.put("id",id);
            entity = new StringEntity(json.toString(), "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

		Request request = new Request.Builder()
				.url(Constant.HOST+Constant.ACTIVITY_DETAIL)
				.post(requestBody)
				.build();

		Call call = WebManager.getInstance(context).okHttpClient.newCall(request);

		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("连接失败");
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String json = response.body().string() ;
				Gson gson = new Gson() ;
				ActDetailBean bean = gson.fromJson ( json, ActDetailBean.class );
				if(mIActManager != null){
					mIActManager.IActivityDetail(bean) ;
				}

			}

		});
    }

    public void activityList(Context context, final IActManager mIActManager) {


		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), "");

		Request request = new Request.Builder()
				.url(Constant.HOST+Constant.ACTIVITY_LIST)
				.post(requestBody)
				.build();

		Call call = WebManager.getInstance(context).okHttpClient.newCall(request);

		call.enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("连接失败");
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String json = response.body().string() ;
				Gson gson = new Gson() ;
				ActListBean bean = gson.fromJson ( json, ActListBean.class );

				if(mIActManager != null){
					mIActManager.IActivityList(bean);
				}

			}

		});

    }

}
