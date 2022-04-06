import java.util.Scanner;

public class Account {
	
	private int balance; 
	private String customerName;
	private String customerId;
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Account() {}
	
	public Account(String customerName, String customerId) {
		this.customerName = customerName;
		this.customerId = customerId;
	}
	
	void deposit(int amount) {  // 입금 함수 
		if(amount != 0) {
			this.balance += amount;
			System.out.println("\n"+amount + "원을 입금하였습니다\n");
		}
		
	}
	
	void withdraw(int amount2) { // 출금 함수 
		if (amount2 != 0) {
			if (this.balance <= 0 || this.balance < amount2) {
				System.out.println("\n잔액이 부족합니다");
				System.out.println("현재" + customerName + "님 의 잔액은 " + this.balance + "원 입니다");
				System.out.println("다시 입력해주세요\n");
			} else {
				this.balance -= amount2;
				System.out.println("\n" + amount2 + "원을 출금하였습니다\n");
			}
		}
	}

	void showMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println(this.customerName +"님 어서오세요" );
		System.out.println("당신의 아이디는 " +this.customerId + "입니다\n");
		
		bank : while(true)  {
			System.out.println("★★★★ 메뉴 ★★★★");
			System.out.println("1. 잔액조회");
			System.out.println("2. 입금");
			System.out.println("3. 출금");
			System.out.println("4. 종료");
			System.out.print("\n메뉴 고르기 >>>> ");
			int menu = Integer.parseInt(sc.nextLine());
			
			switch(menu){
			
			case 1 :
					System.out.println("\n===========================");
					System.out.println("잔액 = " + balance +"원");
					System.out.println("=========================== \n");
					break;
			
			case 2 : 
					System.out.println("얼마를 입금 하시겠습니까? ");
					int amount = Integer.parseInt(sc.nextLine());
					deposit(amount);
					break;
		
					
			case 3 : 
					System.out.println("얼마를 출금 하시겠습니까? ");
					int amount2 = Integer.parseInt(sc.nextLine());
					withdraw(amount2);	
					break;
					
			case 4 :
					System.out.println("프로그램을 종료 합니다\n");
					break bank;
			
			default : 
					System.out.println("1번,2번,3번,4번 중에서 입력해주세요 \n");
					break;
				}
			}
		}
	}
