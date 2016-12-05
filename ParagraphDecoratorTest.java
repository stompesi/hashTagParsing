import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ParagraphDecoratorTest {

	@Test
	public void test() {
		final String[] inputs = { "#나 &nbsp;만의 #맛집", "# &nbsp;맛집 #헬로", "<a href=\"#ttad\" />#맛집###ㅎㅎㅎ#</a>", "###맛집",
				"#나의##맛집",
				"<span>&nbsp; &nbsp; &nbsp; #가나다라 &nbsp; &nbsp; &nbsp;<br></span><span>&nbsp; &nbsp; &nbsp;&nbsp;<a href=\"http://www.naver.com\" target=\"_blank\" class=\"se_link\">#링크</a>&nbsp; &nbsp; &nbsp;&nbsp;</span>",
				"<span>&nbsp; &nbsp; &nbsp; #가나다라 &nbsp; &nbsp; &nbsp;<br></span><span>&nbsp; &nbsp; &nbsp;&nbsp;<a href=\"http://www.naver.com\" target=\"_blank\" class=\"se_link\">#링크<</a>&nbsp; &nbsp; &nbsp;&nbsp;</span>",
				"<a href=\"#test\">헬로</a>", "#가나다라", "가나다라#", "#가나다라#", "#가#나#다#라", "가#나다라", "가#나다#라#", "가##나다#라",
				"가##나다#라#", "#hello#world" };

		final String[] expects = { "<span class=\"__se3tag\">#나</span> &nbsp;만의 <span class=\"__se3tag\">#맛집</span>",
				"# &nbsp;맛집 <span class=\"__se3tag\">#헬로</span>",
				"<a href=\"#ttad\" /><span class=\"__se3tag\">#맛집</span>##<span class=\"__se3tag\">#ㅎㅎㅎ</span>#</a>",
				"##<span class=\"__se3tag\">#맛집</span>",
				"<span class=\"__se3tag\">#나의</span>#<span class=\"__se3tag\">#맛집</span>",
				"<span>&nbsp; &nbsp; &nbsp; <span class=\"__se3tag\">#가나다라</span> &nbsp; &nbsp; &nbsp;<br></span><span>&nbsp; &nbsp; &nbsp;&nbsp;<a href=\"http://www.naver.com\" target=\"_blank\" class=\"se_link\"><span class=\"__se3tag\">#링크</span></a>&nbsp; &nbsp; &nbsp;&nbsp;</span>",
				"<span>&nbsp; &nbsp; &nbsp; <span class=\"__se3tag\">#가나다라</span> &nbsp; &nbsp; &nbsp;<br></span><span>&nbsp; &nbsp; &nbsp;&nbsp;<a href=\"http://www.naver.com\" target=\"_blank\" class=\"se_link\"><span class=\"__se3tag\">#링크<</span></a>&nbsp; &nbsp; &nbsp;&nbsp;</span>",
				"<a href=\"#test\">헬로</a>", "<span class=\"__se3tag\">#가나다라</span>", "가나다라#",
				"<span class=\"__se3tag\">#가나다라</span>#",
				"<span class=\"__se3tag\">#가</span><span class=\"__se3tag\">#나</span><span class=\"__se3tag\">#다</span><span class=\"__se3tag\">#라</span>",
				"가<span class=\"__se3tag\">#나다라</span>",
				"가<span class=\"__se3tag\">#나다</span><span class=\"__se3tag\">#라</span>#",
				"가#<span class=\"__se3tag\">#나다</span><span class=\"__se3tag\">#라</span>",
				"가#<span class=\"__se3tag\">#나다</span><span class=\"__se3tag\">#라</span>#",
				"<span class=\"__se3tag\">#hello</span><span class=\"__se3tag\">#world</span>"
		};

		ParagraphDecorator builder = ParagraphDecorator.builder();

		for (int i = 0; i < inputs.length; i++) {
			String input = inputs[i];
			String expect = expects[i];

			String result = builder.process(input);

			System.out.println(result);
			assertThat(result, is(expect));
		}
	}
}