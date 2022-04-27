package javafortester;


public class Topic_05_Split {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String oldUrl = "167K lượt thích";
		
		String urlValue[] = oldUrl.split("K");//cắt nhau bằng tại //
		
		//http:
		//the-internet.herokuapp.com/basic_auth
		System.out.println(urlValue[0]);
		System.out.println(urlValue[1]);
//		
//		String  likes = "1,933 likes";
//		String likeNumberString = likes.split(" ")[0].replace(",", "");
//		System.out.println(likeNumberString);
		
		String  likes = "167K lượt thích";
		String likeNumberString = likes.split("K")[0];
		System.out.println(likeNumberString);
	}

}
