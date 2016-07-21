import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomizedReplaceAll{
	public static void main(String[] args) throws Exception {
		String s="    // card.setCard_type(WeChatCardTypeEnum.GROUPON);\n" +
				"    // card.setBind_openid(false);\n" +
				"    // card.setBrand_name(\"haidilao\");\n" +
				"    // card.setCan_give_friend(true);\n" +
				"    // card.setCan_share(true);\n" +
				"    // card.setCode_type(\"CODE_TYPE_TEXT\");\n" +
				"    // card.setColor(\"Color010\");\n" +
				"    // card.setCustom_url(\"http://www.qq.com\");\n" +
				"    // card.setCustom_url_name(\"use\");\n" +
				"    // card.setCustom_url_sub_title(\"tip\");\n" +
				"    // card.setDateInfoType(WeChatCardDateInfoTypeEnum.DATE_TYPE_FIX_TIME_RANGE);\n" +
				"    // card.setBegin_timestamp(new DateTime(\"2015-10-24\"));\n" +
				"    // card.setEnd_timestamp(new DateTime(\"2015-10-26\"));\n" +
				"    // card.setDescription(\"description\");\n" +
				"    // card.setGet_limit(3);\n" +
				"    // // card.getLocation_id_list().add(123L);\n" +
				"    // card.setLogo_url(\"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0\");\n" +
				"    // card.setNotice(\"notice\");\n" +
				"    // card.setPromotion_url(\"http://www.qq.com\");\n" +
				"    // card.setPromotion_url_name(\"promot name\");\n" +
				"    // card.setService_phone(\"020-777778787\");\n" +
				"    // card.setQuantity(1000);\n" +
				"    // card.setSource(\"dianping\");\n" +
				"    // card.setSub_title(\"weekend must\");\n" +
				"    // card.setTitle(\"huoguo taocan\");\n" +
				"    // card.setUse_custom_code(false);\n" +
				"    // card.setDeal_detail(\"huoguo detail\");";
		Pattern p = Pattern.compile("_[a-z]");
		Matcher m = p.matcher(s);
		StringBuffer sb = new StringBuffer();
		String ss=s;
		while(m.find()) {
			System.out.println("Match " + m.group() + " at positions " +
					m.start() + "-" + (m.end() - 1));
			String strMatch = m.group();
			ss=ss.replaceAll(strMatch,strMatch.substring(1).toUpperCase());
		}
		System.out.print(ss);
	}
	
}