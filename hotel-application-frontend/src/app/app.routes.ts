import { Routes } from '@angular/router';
import { IndexPageComponent } from './components/index-page/index-page.component';
import { HotelDetailsPageComponent } from './components/hotel-details-page/hotel-details-page.component';
import { HotelListPageComponent } from './components/hotel-list-page/hotel-list-page.component';

export const routes: Routes = [
    {
        path: '',
        component: IndexPageComponent
    },
    {
        path: 'hotel-details/:id',
        component: HotelDetailsPageComponent
    },
    {
        path: 'hotels-list',
        component: HotelListPageComponent
    }
];
