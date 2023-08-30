import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/models/transaction.model';
import { RewardService } from 'src/app/services/reward.service';

@Component({
  selector: 'app-reward-details',
  templateUrl: './reward-details.component.html',
  styleUrls: ['./reward-details.component.css']
})
export class RewardDetailsComponent implements OnInit {

  rewards: any;

  constructor(private rewardService: RewardService) { }

  ngOnInit(): void {
    this.getRewardPoints();
  }

  getRewardPoints(): void {
    this.rewardService.getRewardPoints().subscribe(
      (data) => {
        this.rewards = data;
        console.log('Fetched reward points:', this.rewards);
      },
      (error) => {
        console.error('Error while fetching reward points:', error);
      }
    );
  }
}
