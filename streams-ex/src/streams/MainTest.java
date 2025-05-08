package streams;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainTest 	{
	public static void main(String[] args) {
		
		// 자바의 함수형 프로그래밍
		
		List<String> list1 = Arrays.asList("토게", "사토루", "메구미", "유우지");
		for(String s : list1) {
			System.out.println(s);
		}
		
		list1.stream().forEach(System.out::println); // 메서드참조
		
		// Math, Arrays, Collections
		List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
		// 형식추론
		list2.stream().filter(n -> n%2 == 0).forEach(System.out::println);
		
		// 형식추론
		list1.stream().map(s -> s.length()).forEach(System.out::println);
		System.out.println("-----------------");
		
		// 빅데이터 수집 -> 하나의 스트림으로 변환 -> 가공 -> 출력 or 새로운 컬렉션
		List<Integer> list3 = Arrays.asList(9, 8, 10, 300, 1, 2, 3, 4, 200, 5, 6, 7, 8, 9);
		list3.stream().distinct().sorted().forEach(System.out::println); // 중복값 제거 및 정렬
		
		
		List<Integer> list4 = Arrays.asList(20, 22, 30, 27, 31, 21);
		// null값을 갖지 않는 자료형
		Optional<Integer> result = list4.stream().reduce((x, y) -> x+y); // 0, 20 / 0+20, 22 / 0+20+22, 30 
		System.out.println(result.get());
	}
}
