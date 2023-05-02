import {Component, Input, OnInit} from '@angular/core';
import {Shipment, TransportType} from "../../models/shipment.mode";
import { Template } from 'src/app/models/template.model';
import {TemplateService} from "src/app/services/template.service";
import { ExecutionPlanService } from "src/app/services/execution-plan.service";
import { ExecutionPlan,ExecutionPlanAction } from 'src/app/models/execution-plan.model';

@Component({
  selector: 'execution-plan-model',
  templateUrl: './execution-plan-model.component.html',
  styleUrls: ['./execution-plan-model.component.scss']
})
export class ExecutionPlanModelComponent implements OnInit {

  @Input()
  shipment: Shipment;
  templates: Template[] = [];
  selectedTemplate!: Template;

  constructor(
      private templateService: TemplateService,
      private executionPlanService: ExecutionPlanService) { }

  ngOnInit(): void {
    this.templateService.getTemplates().subscribe(value => {

      this.templates = value
    });
 }

  onChange(template: Template, event: any) {
    this.selectedTemplate = template
  } 

  createExecutionPlan(){
    if(this.shipment != null && this.selectedTemplate != null){
      
        var planActions: ExecutionPlanAction[] = this.selectedTemplate.actions?.map(action => ({
          actionName: action.name,
          isExecuted: true,
          isNotify: false
        })) || [];


      var plan : ExecutionPlan = {
        origin : this.shipment.origin,
        destination: this.shipment.destination,
        customerId: this.shipment.customerId,
        transportType: this.shipment.transportType.toString(),
        temperature: { 
                    min : this.shipment.temperature.min,
                    max: this.shipment.temperature.max},
        fragile: this.shipment.fragile,
        notifyCustomer: this.shipment.notifyCustomer,
        templateId: this.selectedTemplate.id,
        executionPlanActions: planActions

      }

      this.executionPlanService.createPlan(plan).subscribe((response: any) => { 
      });
    }

    else
    {
      alert("Please be aware that shipment and template should be selected ")
    }
  }

}