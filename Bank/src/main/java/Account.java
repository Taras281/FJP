public class Account {

  private long money;
  private final String accNumber;
  private boolean isBlocked;

  public Account(String accNumber, long money) {
    this.accNumber = accNumber;
    this.money = money;
    isBlocked = false;
  }

  public long getMoney() {
    if (getStatus()) {
      return 0;
    } else {
      return money;
    }
  }

  public void setMoney(long money) {
    this.money = money;
  }

  public String getAccNumber() {
    return accNumber;
  }

  public void blockedAccount() {
    isBlocked = true;
  }

  public boolean getStatus() {
    return isBlocked;
  }

  @Override
  public String toString() {
    return "Номер аккаунта: " + getAccNumber() + ". Остаток на счёте: " + getMoney() + " руб.";
  }
}
