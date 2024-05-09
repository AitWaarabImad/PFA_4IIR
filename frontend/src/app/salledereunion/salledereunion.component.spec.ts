import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalledereunionComponent } from './salledereunion.component';

describe('SalledereunionComponent', () => {
  let component: SalledereunionComponent;
  let fixture: ComponentFixture<SalledereunionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SalledereunionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SalledereunionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
