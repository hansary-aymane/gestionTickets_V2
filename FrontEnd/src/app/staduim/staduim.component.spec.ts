import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaduimComponent } from './staduim.component';

describe('StaduimComponent', () => {
  let component: StaduimComponent;
  let fixture: ComponentFixture<StaduimComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StaduimComponent]
    });
    fixture = TestBed.createComponent(StaduimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
