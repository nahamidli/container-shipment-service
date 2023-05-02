import { Component, OnInit } from '@angular/core';
import { ExecutionPlan } from 'src/app/models/execution-plan.model';
import {ExecutionPlanService} from "src/app/services/execution-plan.service";

@Component({
  selector: 'execution-plan-component',
  templateUrl: './execution-plan-component.component.html',
  styleUrls: ['./execution-plan-component.component.scss']
})
export class ExecutionPlanComponentComponent implements OnInit {

  executionPlans: ExecutionPlan[] = [];

  constructor(private executionPlanService: ExecutionPlanService) { }

  ngOnInit(): void {
    this.executionPlanService.getAllPlans().subscribe(value => {
      this.executionPlans = value
    });
  }

}