import {Shipment, TransportType} from "../models/shipment.mode";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root',
})
export class ShipmentService {

    API_ENDPOINT : string = "http://localhost:8080/shipments"
    private shipments: Shipment[] = [];

    constructor(private http:HttpClient) { }

    getShipments() {
        return this.http.get<Shipment[]>(this.API_ENDPOINT);
    }

    getShipmentById(shipmentId: Number): Shipment | null {
        return this.shipments.find(it => it.id == shipmentId) || null;
    }

}