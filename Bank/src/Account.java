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
	
	void deposit(int amount) {  // �Ա� �Լ� 
		if(amount != 0) {
			this.balance += amount;
			System.out.println("\n"+amount + "���� �Ա��Ͽ����ϴ�\n");
		}
		
	}
	
	void withdraw(int amount2) { // ��� �Լ� 
		if (amount2 != 0) {
			if (this.balance <= 0 || this.balance < amount2) {
				System.out.println("\n�ܾ��� �����մϴ�");
				System.out.println("����" + customerName + "�� �� �ܾ��� " + this.balance + "�� �Դϴ�");
				System.out.println("�ٽ� �Է����ּ���\n");
			} else {
				this.balance -= amount2;
				System.out.println("\n" + amount2 + "���� ����Ͽ����ϴ�\n");
			}
		}
	}

	void showMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println(this.customerName +"�� �������" );
		System.out.println("����� ���̵�� " +this.customerId + "�Դϴ�\n");
		
		bank : while(true)  {
			System.out.println("�ڡڡڡ� �޴� �ڡڡڡ�");
			System.out.println("1. �ܾ���ȸ");
			System.out.println("2. �Ա�");
			System.out.println("3. ���");
			System.out.println("4. ����");
			System.out.print("\n�޴� ���� >>>> ");
			int menu = Integer.parseInt(sc.nextLine());
			
			switch(menu){
			
			case 1 :
					System.out.println("\n===========================");
					System.out.println("�ܾ� = " + balance +"��");
					System.out.println("=========================== \n");
					break;
			
			case 2 : 
					System.out.println("�󸶸� �Ա� �Ͻðڽ��ϱ�? ");
					int amount = Integer.parseInt(sc.nextLine());
					deposit(amount);
					break;
		
					
			case 3 : 
					System.out.println("�󸶸� ��� �Ͻðڽ��ϱ�? ");
					int amount2 = Integer.parseInt(sc.nextLine());
					withdraw(amount2);	
					break;
					
			case 4 :
					System.out.println("���α׷��� ���� �մϴ�\n");
					break bank;
			
			default : 
					System.out.println("1��,2��,3��,4�� �߿��� �Է����ּ��� \n");
					break;
				}
			}
		}
	}
