import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
	private Scanner sc;
	private int lastId = 0;
	private List<WiseSaying> wiseSayings;

	public App() {
		sc = new Scanner(System.in);
		wiseSayings = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		while (true) {
			System.out.print("명령어 : ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}
			if (cmd.equals("등록")) {
				int id = lastId + 1;
				String regDate = Time.regDate();
				System.out.print("명언 : ");
				String content = sc.nextLine().trim();
				System.out.print("작가 : ");
				String author = sc.nextLine().trim();

				WiseSaying wiseSaying = new WiseSaying(id, regDate, content, author);
				wiseSayings.add(wiseSaying);

				System.out.printf("%d번 명언이 등록되었습니다.\n", id);
				lastId++;
			}
			if (cmd.equals("목록")) {
				if (wiseSayings.size() == 0) {
					System.out.println("등록된 명언이 없습니다.");
					continue;
				}
				System.out.println("번호  /  작가  /  명언");
				for (int i = wiseSayings.size() - 1; i >= 0; i--) {
					WiseSaying ws = wiseSayings.get(i);
					System.out.printf("%d  /  %s  /  %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
				}
			}
			if (cmd.startsWith("삭제")) {
				String[] cmdBits = cmd.split("\\?");
				if (cmdBits.length == 1) {
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				HashMap<String, String> params = new HashMap<>();
				for (String paramStr : cmdBits) {
					String[] paramStrBits = paramStr.split("=");
					if (paramStrBits.length == 1) {
						continue;
					}
					String key = paramStrBits[0];
					String value = paramStrBits[1];
					params.put(key, value);
				}
				int id = -1;
				try {
					id = Integer.parseInt(params.get("id"));
				} catch (NumberFormatException e) {
					System.out.println("ID(정수)를 제대로 입력하세요.");
				}
				if (id == -1) {
					System.out.println("ID를 입력하세요.");
					continue;
				}

				WiseSaying wiseSaying = findId(id);

				if (wiseSaying == null) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
					continue;
				}
				wiseSayings.remove(wiseSaying);
				System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
			} else if (cmd.startsWith("상세보기")) {
				String[] cmdBits = cmd.split("\\?");
				if (cmdBits.length == 1) {
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				HashMap<String, String> params = new HashMap<>();
				for (String paramStr : cmdBits) {
					String[] paramStrBits = paramStr.split("=");
					if (paramStrBits.length == 1) {
						continue;
					}
					String key = paramStrBits[0];
					String value = paramStrBits[1];
					params.put(key, value);
				}
				int id = -1;
				try {
					id = Integer.parseInt(params.get("id"));
				} catch (NumberFormatException e) {
					System.out.println("ID(정수)를 제대로 입력하세요.");
				}
				if (id == -1) {
					System.out.println("ID를 입력하세요.");
					continue;
				}

				WiseSaying wiseSaying = findId(id);

				if (wiseSaying == null) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.println("번호 :" + id);
				System.out.println("날짜 :" + wiseSaying.regDate);
				System.out.println("작가 :" + wiseSaying.author);
				System.out.println("내용 :" + wiseSaying.content);

			} else if (cmd.startsWith("수정")) {
				String[] cmdBits = cmd.split("\\?");
				if (cmdBits.length == 1) {
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				HashMap<String, String> params = new HashMap<>();
				for (String paramStr : cmdBits) {
					String[] paramStrBits = paramStr.split("=");
					if (paramStrBits.length == 1) {
						continue;
					}
					String key = paramStrBits[0];
					String value = paramStrBits[1];
					params.put(key, value);
				}
				int id = -1;
				try {
					id = Integer.parseInt(params.get("id"));
				} catch (NumberFormatException e) {
					System.out.println("ID(정수)를 제대로 입력하세요.");
				}
				if (id == -1) {
					System.out.println("ID를 입력하세요.");
					continue;
				}

				WiseSaying wiseSaying = findId(id);

				if (wiseSaying == null) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("명언(기존) :%s\n", wiseSaying.getContent());
				System.out.printf("작가(기존) :%s\n", wiseSaying.getAuthor());

				System.out.print("명언 : ");
				String content = sc.nextLine().trim();
				System.out.print("작가 : ");
				String author = sc.nextLine().trim();

				wiseSaying.setContent(content);
				wiseSaying.setAuthor(author);

				System.out.printf("%d번 명언이 수정되었습니다.\n", id);

			} else {
				System.out.println("명령어를 제대로 입력하세요.");
			}
		}
		sc.close();

	}

	private WiseSaying findId(int id) {
		for (WiseSaying wiseSaying : wiseSayings) {
			if (wiseSaying.getId() == id) {
				return wiseSaying;
			}
		}
		return null;
	}

}
