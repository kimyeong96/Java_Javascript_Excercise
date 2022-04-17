package com.kh.miniproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Run {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MemberDAO memDAO = MemberDAO.getInstance();
		PostDAO postDAO = PostDAO.getInstance();
		CommentDAO comDAO = CommentDAO.getInstance();

		menu: while (true) {
			System.out.println("Facebook 프로그램");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("0. 프로그램 종료");
			String menu = sc.nextLine();

			if (menu.equals("1")) { // 로그인
				System.out.print("id 입력 >>");
				String id = sc.nextLine();
				System.out.print("pw 입력 >>");
				String pw = sc.nextLine();

				try {
					boolean checkLogin = memDAO.loginCheck(id, pw);

					if (checkLogin) {
						System.out.println("*** " + memDAO.getNickname(id) + "님 환영합니다 ***");

						post: while (true) { // 포스트 시작
							System.out.println("\n1.포스트 등록");
							System.out.println("2. 포스트 목록");
							System.out.println("3. 포스트 확인");
							System.out.println("4. 포스트 수정");
							System.out.println("5. 포스트 삭제");
							System.out.println("6. 로그아웃");
							System.out.print(">>");
							String postMenu = sc.nextLine();

							if (postMenu.equals("1")) { // 포스트 등록
								System.out.println("=== 포스트 등록 ===");
								String title;
								String content;
								try {
									title: while (true) { // 제목
										System.out.print("타이틀(10자 이내) >>");
										title = sc.nextLine();
										if (title.length() > 10) {
											System.out.println("타이틀은 10자 이내로 입력하세요");
											continue;
										}
										break title;
									}
									content: while (true) { // 내용
										System.out.print("내용(100자 이내) >>");
										content = sc.nextLine();
										if (content.length() > 100) {
											System.out.println("내용은 100자 이내로 입력하세요.");
											continue;
										}
										break content;
									}
									PostDTO dto = new PostDTO(0, title, content, memDAO.getNickname(id), null);
									postDAO.insert(dto);
									System.out.println("포스트 등록 완료!");
								} catch (Exception e) {
									e.printStackTrace();
								}

							} else if (postMenu.equals("2")) { // 포스트 목록
								try {
									ArrayList<PostDTO> list = postDAO.selectAll();

									if (list != null) {
										System.out.println("=== 포스트 목록 ===");
										System.out.println("글번호\t타이틀\t닉네임\t등록일");
										for (PostDTO dto : list) {
											System.out.println(dto.toString());
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							} else if (postMenu.equals("3")) { // 포스트 확인
								try {
									ArrayList<PostDTO> list = postDAO.selectAll();
									if (list != null) {
										System.out.println("=== 포스트 목록 ===");
										System.out.println("글번호\t타이틀\t닉네임\t등록일");
										for (PostDTO postDTO : list) {
											System.out.println(postDTO.toString());
										}
									} else {
										System.out.println("데이터 조회 실패");
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								System.out.print("확인할 포스트의 번호를 입력하세요 >> ");
								int postno = Integer.parseInt(sc.nextLine());
								try {
									PostDTO rs = postDAO.select(postno);
									if (rs == null) {
										System.out.println("포스트 조회 실패");
										continue;
									} else {
										System.out.println("=== 포스트 확인 ===");
										System.out.println("글번호\t" + rs.getNo());
										System.out.println("닉네임\t" + rs.getNickname());
										System.out.println("등록일\t" + rs.getRegister_date());
										System.out.println("타이틀\t" + rs.getTitle());
										System.out.println("내용\t" + rs.getContent());
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									ArrayList<CommentDTO> list = comDAO.selectAll();

									if (list != null) { // 댓글 확인
										System.out.println("=== 댓글 목록 ===");
										System.out.println("댓글내용\t닉네임\t작성일");
										for (CommentDTO comDTO : list) {
											System.out.println(comDTO.toString());
										}
									} else {
										System.out.println("데이터 조회 실패");
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								System.out.println("1. 댓글 등록");
								System.out.println("2. 메뉴로 돌아가기");
								System.out.print(">> ");
								String cmenu = sc.nextLine();

								if (cmenu.equals("1")) { // 댓글 등록
									System.out.print("댓글 입력(50자 이내) >> ");
									String content = sc.nextLine();

									try {
										int rs = comDAO.insert(new CommentDTO(content, memDAO.getNickname(id), null));
										if (rs > 0) {
											System.out.println("댓글 등록 완료");
										} else {
											System.out.println("댓글 등록 실패");
										}
									} catch (Exception e) {
										e.printStackTrace();
										System.out.println("오류가 발생했습니다.");
									}
								} else if (cmenu.equals("2")) { // 메뉴로 돌아가기
									break;
								}

							} else if (postMenu.equals("4")) { // 포스트 수정
								try {
									String title;
									String content;
									update: while (true) {
										ArrayList<PostDTO> list = postDAO.selectAll();
										if (list != null) {
											System.out.println("=== 포스트 목록 ===");
											System.out.println("글번호\t타이틀\t닉네임\t\t등록일");
											for (PostDTO postDTO : list) {
												System.out.println(postDTO.toString());
											}
											System.out.print("수정할 포스트의 번호를 입력하세요 >> ");
											int no = Integer.parseInt(sc.nextLine());
											title: while (true) {
												System.out.print("수정할 타이틀(10자 이내) >> ");
												title = sc.nextLine();
												if (title.length() > 10) {
													System.out.println("타이틀은 10자 이내로 입력해주세요.");
													continue;
												}
												break title;
											}
											content: while (true) {
												System.out.print("수정할 내용(100자 이내) >> ");
												content = sc.nextLine();

												if (content.length() > 100) {
													System.out.println("내용은 100자 이내로 입력해주세요");
													continue;
												}
												break content;
											}
											PostDTO dto = new PostDTO(no, title, content);
											int rs = postDAO.update(dto);
											if (rs > 0) {
												System.out.println("포스트 수정 완료!");
												break update;
											} else {
												System.out.println("없는 번호입니다. 다시 입력해주세요.");
											}
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else if (postMenu.equals("5")) { // 포스트 삭제
								try {
									ArrayList<PostDTO> list = postDAO.selectAll();
									System.out.println("글번호\t타이틀\t\t\t닉네임\t\t등록일");
									for (PostDTO dto : list) {
										System.out.println(dto.toString());
									}
									System.out.print("삭제할 포스트의 번호를 입력하세요 >> ");
									int no = Integer.parseInt(sc.nextLine());
									System.out.print("정말 삭제하시겠습니까?(Y/N) >> ");
									String yesOrNo = (sc.nextLine()).toUpperCase();

									if (yesOrNo.equals("Y")) {
										int rs = postDAO.delete(no);

										if (rs > 0) {
											System.out.println("삭제가 완료되었습니다");
										} else {
											System.out.println("삭제할 포스트가 없습니다.");
										}
									} else if (yesOrNo.equals("N"))
										System.out.println("삭제를 중단합니다");

								} catch (Exception e) {
									e.printStackTrace();
								}

							} else if (postMenu.equals("6")) { // 포스트 로그아웃
								break post;
							}
						}

					} else {
						System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("DB서버가 불안정합니다");
				}

			} else if (menu.equals("2")) { // 회원가입
				String id;
				while (true) {
					System.out.print("아이디 입력>> ");
					id = sc.nextLine();
					try {
						if (!memDAO.idCheck(id)) { // id 중복확인
							System.out.println("존재하는 id 입니다. 다른 id를 사용하세요.");
							continue;
						} else {
							System.out.println("사용가능한 id 입니다.");
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.print("비밀번호 입력>> ");
				String pw = sc.nextLine();
				String nickname;
				while (true) {
					System.out.print("닉네임 입력>> ");
					nickname = sc.nextLine();
					try {
						if (!memDAO.nicknameCheck(nickname)) {
							System.out.println("존재하는 닉네임 입니다. 다른 닉네임을 사용하세요.");
							continue;
						} else {
							System.out.println("사용가능한 닉네임 입니다.");
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				MemberDTO dto = new MemberDTO(id, pw, nickname);
				try {
					int rs = memDAO.insert(dto);
					if (rs > 0) {
						System.out.println("회원가입에 성공하였습니다.");

					} else {
						System.out.println("실패");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("오류가 발생했습니다. 관리자에게 문의하세요");
				}

			} else if (menu.equals("0")) { // 종료
				System.out.println("프로그램을 종료합니다");
				break menu;
			}
		}

	}
}
