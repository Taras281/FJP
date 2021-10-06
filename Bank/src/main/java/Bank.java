import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Bank {

  private static final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();
  private final Random random = new Random();

  public boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException {
    Thread.sleep(1000);
    return random.nextBoolean();
  }

  public void addAccountToBank(Account account) {
    accounts.put(account.getAccNumber(), account);
  }

  public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {
    Account fromAccount = accounts.get(fromAccountNum);
    Account toAccount = accounts.get(toAccountNum);
    boolean check = false;
    if (isEnoughMoney(fromAccount.getMoney(), amount)) {
      long verificationLimit = 500_000;
      if (amount > verificationLimit) {
        try {
          check = isFraud(fromAccountNum, toAccountNum, amount);
        } catch (InterruptedException exception) {
          exception.printStackTrace();
        }
        if (check) {
          fromAccount.blockedAccount();
          toAccount.blockedAccount();
        } else {
          toAccount.setMoney(toAccount.getMoney() + amount);
          fromAccount.setMoney(fromAccount.getMoney() - amount);
        }
      } else {
        toAccount.setMoney(toAccount.getMoney() + amount);
        fromAccount.setMoney(fromAccount.getMoney() - amount);
      }
    }
  }

  public synchronized void getAccounts() {
    System.out.println(accounts.keySet());
  }

  public boolean isEnoughMoney(long fromAccountMoney, long amount) {
    return fromAccountMoney >= amount;
  }

  public synchronized long getBalance(String accountNum) {
    if (accounts.get(accountNum).getStatus()) {
      System.out.println("Счет " + accountNum + " заблокирован.");
      return 0;
    } else {
      System.out.println(accountNum + " - " + accounts.get(accountNum).getMoney() + " руб.");
      return accounts.get(accountNum).getMoney();
    }
  }

  public synchronized long getSumAllAccounts() {
    long sumMoney = 0;
    for (Map.Entry<String, Account> item : accounts.entrySet()) {
      sumMoney += item.getValue().getMoney();
    }
    System.out.println("Общая сумма на всех счетах: " + sumMoney + " руб.");
    return sumMoney;
  }
}
