import { Besoin } from "../besoin/besoin";

export class BesoinProposeDetails {
  constructor(
    public id: number,
    public prix: number,
    public marque: string,
    public besoin: Besoin
  ) {}
}
