import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreersalledereunionComponent } from './creersalledereunion.component';

describe('CreersalledereunionComponent', () => {
  let component: CreersalledereunionComponent;
  let fixture: ComponentFixture<CreersalledereunionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreersalledereunionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreersalledereunionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
