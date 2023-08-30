export class Transaction {

    constructor(private customerId: number, private purchaseAmount: number, private date: Date) { }

    getCustomerId(): number {
        return this.customerId;
    }

    setCustomerId(customerId: number): void {
        this.customerId = customerId;
    }

    getPurchaseAmount(): number {
        return this.purchaseAmount;
    }

    setPurchaseAmount(purchaseAmount: number): void {
        this.purchaseAmount = purchaseAmount;
    }

    getDate(): Date {
        return this.date;
    }

    setDate(date: Date): void {
        this.date = date;
    }
}
