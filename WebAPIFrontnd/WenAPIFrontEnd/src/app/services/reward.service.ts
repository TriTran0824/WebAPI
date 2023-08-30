import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from 'src/app/models/transaction.model';

const basedAPIUrl = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class RewardService {

  private rewardPoints = "web-api/v1/reward/rewardPoints?filename=transaction.txt";

  constructor(private http: HttpClient) { }

  getRewardPoints(): Observable<any> {
    return this.http.get<any>(`${basedAPIUrl}/${this.rewardPoints}`);
  }
}


