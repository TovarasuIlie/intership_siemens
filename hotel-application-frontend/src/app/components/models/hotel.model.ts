import { RoomInterface } from "./room.model";

export interface HotelInterface {
    id: number,
    name: string,
    latitude: number,
    longitude: number,
    rooms: RoomInterface[]
}